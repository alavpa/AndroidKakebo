package com.alavpa.androidkakebo.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.alavpa.androidkakebo.R

open class LoadingDialog : DialogFragment() {

    private lateinit var tvLoading: TextView
    private lateinit var message: String

    companion object {
        private const val TAG = "TAG_LOADING"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvLoading = view.findViewById(R.id.tv_loading)
        tvLoading.text = message
    }

    fun show(parent: FragmentActivity, message: String) {
        this.message = message
        show(parent.supportFragmentManager, TAG)
    }
}