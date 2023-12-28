package com.example.testfluttermodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.testfluttermodel.data.CommonRepositoryImpl
import com.example.testfluttermodel.data.FlutterChannel
import io.flutter.embedding.android.FlutterFragment

private const val TAG = "CustomFlutterFragment"
class CustomFlutterFragment : FlutterFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")

        FlutterChannel(
            messenger = flutterEngine!!.dartExecutor,
            scope = lifecycleScope,
            commonRepository = CommonRepositoryImpl()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}