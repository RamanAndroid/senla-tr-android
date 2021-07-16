package com.example.senlastudy.fragments.share

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.senlastudy.MovieApplication
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

    companion object {
        private const val REQUEST_CODE_PERMISSION_READ_CONTACTS = 404
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
        Toast.makeText(
            requireContext(),
            "Name ${contact.nameContact}, number ${contact.numberContact}",
            Toast.LENGTH_SHORT
        ).show()
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
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_PERMISSION_READ_CONTACTS
            )
        }
    }
}