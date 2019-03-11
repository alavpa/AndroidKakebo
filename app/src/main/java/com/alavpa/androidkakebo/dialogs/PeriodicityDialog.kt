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
import kotlinx.android.synthetic.main.dialog_periodicity.number
import kotlinx.android.synthetic.main.dialog_periodicity.period

open class PeriodicityDialog : DialogFragment() {

    interface ConfirmationListener {
        fun onAccept(number: Int, period: Int)
        fun onCancel()
    }

    companion object {
        private const val TAG = "TAG_PERIODICITY"

        fun newInstance(): PeriodicityDialog {
            return PeriodicityDialog()
        }
    }

    var onConfirmationListener: ConfirmationListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_periodicity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accept?.setOnClickListener {
            onConfirmationListener?.onAccept(
                number?.text.toString().toIntOrNull() ?: 0,
                period?.selectedItemPosition ?: 0
            )
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