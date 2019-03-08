package com.alavpa.androidkakebo.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.alavpa.androidkakebo.R
import kotlinx.android.synthetic.main.dialog_confirmation.accept
import kotlinx.android.synthetic.main.dialog_confirmation.cancel
import kotlinx.android.synthetic.main.dialog_confirmation.message

open class ConfirmationDialog : DialogFragment() {

    interface ConfirmationListener {
        fun onAccept()
        fun onCancel()
    }

    companion object {
        private const val TAG = "TAG_CONFIRMATION"
        private const val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(message: String): ConfirmationDialog {
            return ConfirmationDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_MESSAGE, message)
                }
            }
        }
    }

    var onConfirmationListener: ConfirmationListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.text = arguments?.getString(ARG_MESSAGE, "") ?: ""
        accept?.setOnClickListener {
            onConfirmationListener?.onAccept()
            dismiss()
        }

        cancel?.setOnClickListener {
            onConfirmationListener?.onCancel()
            dismiss()
        }
    }

    fun show(parent: FragmentActivity?, onConfirmationListener: ConfirmationListener) {
        parent?.let {
            show(it.supportFragmentManager, TAG)
        }
        this.onConfirmationListener = onConfirmationListener
    }
}