package com.example.senlastudy.fragments.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.senlastudy.databinding.FragmentContactBinding
import com.example.senlastudy.fragments.BaseFragment
import com.example.senlastudy.presenter.share.ContactListContract
import com.example.senlastudy.presenter.share.ContactListPresenter


class ContactFragment :
    BaseFragment<ContactListContract.PresenterContactList, ContactListContract.ViewContactList>(),
    ContactListContract.ViewContactList {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun createPresenter(): ContactListContract.PresenterContactList {
        return ContactListPresenter()
    }

    override fun setData() {

    }

    override fun errorResponse(throwable: Throwable) {

    }

    override fun showViewLoading() {

    }

    override fun hideViewLoading() {

    }

}