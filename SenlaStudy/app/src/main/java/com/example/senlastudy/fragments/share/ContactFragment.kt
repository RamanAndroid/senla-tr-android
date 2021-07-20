package com.example.senlastudy.fragments.share

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.senlastudy.MovieApplication
import com.example.senlastudy.ShareActivity
import com.example.senlastudy.adapter.ContactAdapter
import com.example.senlastudy.database.entity.Contact
import com.example.senlastudy.databinding.FragmentContactListBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.share.ContactListContract
import com.example.senlastudy.presenter.share.ContactListPresenter


class ContactFragment :
    BaseFragment<ContactListContract.PresenterContactList, ContactListContract.ViewContactList>(),
    ContactListContract.ViewContactList, ContactAdapter.OnContactClickListener {

    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private val adapter: ContactAdapter by lazy { ContactAdapter(this) }
    private var movieTitle: String = ""
    private var movieVoteCount: String = ""
    private var movieVoteAverage: String = ""

    companion object {
        private const val REQUEST_CODE_PERMISSION_READ_CONTACTS = 404
        private const val REQUEST_CODE_PERMISSION_SEND_SMS = 403
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieTitle =
            arguments?.getString(ShareActivity.MOVIE_EXTRA_TITLE)
                ?: error("cannot find movie title")
        movieVoteCount =
            arguments?.getString(ShareActivity.MOVIE_EXTRA_VOTE_COUNT)
                ?: error("cannot find movie vote count")
        movieVoteAverage =
            arguments?.getString(ShareActivity.MOVIE_EXTRA_VOTE_AVERAGE)
                ?: error("cannot find movie vote average")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        initializationAttributes()
        return binding.root
    }

    private fun initializationAttributes() {
        binding.rvContactList.adapter = adapter
        binding.rvContactList.setHasFixedSize(true)
        binding.rvContactList.layoutManager =
            LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissionOfRead()
        binding.requestPermission.setOnClickListener {
            checkPermissionOfRead()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    binding.layoutNoPermission.isVisible = false
                    getContact()
                } else if (grantResults.isNotEmpty() && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "Дайте разрешение", Toast.LENGTH_LONG).show()
                } else {
                    binding.layoutNoPermission.isVisible = true
                }
            }
        }
    }

    override fun createPresenter(): ContactListContract.PresenterContactList {
        return ContactListPresenter(MovieApplication.movieContactsDaoImplDao)
    }

    override fun setData(contactList: List<Contact>) {
        adapter.setData(contactList)
        adapter.notifyDataSetChanged()
    }

    private fun getContact() {
        getPresenter().getContactList()
    }

    override fun errorResponse(throwable: Throwable) {
        binding.rvContactList.isVisible = false
        binding.downloadContact.isVisible = false
        binding.noContact.isVisible = true
    }

    override fun showViewLoading() {
        binding.downloadContact.isVisible = true
        binding.rvContactList.isVisible = false
    }

    override fun hideViewLoading() {
        binding.downloadContact.isVisible = false
        binding.rvContactList.isVisible = true
    }

    override fun onContactClick(contact: Contact) {

        val uri: Uri = Uri.parse("smsto:${contact.numberContact}")
        val intent = Intent(Intent.ACTION_SENDTO, uri)
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "$movieTitle у этого фильма такая оценка $movieVoteAverage($movieVoteCount)"
        )
        startActivity(intent)

        /*
        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(
            contact.nameContact,
            null,
            "$movieTitle у этого фильма такая оценка $movieVoteAverage($movieVoteCount)",
            null,
            null
        )

         */

    }

    private fun checkPermissionOfRead() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            binding.layoutNoPermission.isVisible = false
            getContact()
        } else {
            binding.layoutNoPermission.isVisible = true
            this.requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
            this.requestPermissions(
                arrayOf(Manifest.permission.SEND_SMS),
                REQUEST_CODE_PERMISSION_SEND_SMS
            )
        }
    }
}