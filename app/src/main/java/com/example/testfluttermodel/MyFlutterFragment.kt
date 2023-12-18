package com.example.testfluttermodel

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.flutter.embedding.android.FlutterFragment

private const val TAG = "MyFlutterFragment"
class MyFlutterFragment : FlutterFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        arguments = Bundle()
        Log.d(TAG, "onAttach: ")
        super.onAttach(context)
    }
}