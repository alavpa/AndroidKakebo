package com.alavpa.androidkakebo.loading

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alavpa.androidkakebo.R

/**
 * Created by alex_avila on 8/11/17.
 */
open class LoadingDialog : DialogFragment() {

    private var tvLoading: TextView? = null
    private var message: String? = null

    companion object {
        private val LOADING_MESSAGE = "LOADING_MESSAGE"
        private val TAG = "TAG_LOADING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvLoading?.text = arguments.getString(LOADING_MESSAGE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.dialog_loading, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvLoading = getView()?.findViewById(R.id.tv_loading)
        tvLoading?.text = message

    }

    fun show(message: String?, parent: AppCompatActivity) {
        this.message = message
        show(parent.supportFragmentManager, TAG)
    }
}