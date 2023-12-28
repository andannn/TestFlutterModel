package com.example.testfluttermodel.data

import android.util.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FlutterChannel(
    messenger: BinaryMessenger,
    scope: CoroutineScope,
    private val commonRepository: CommonRepository
) {
    private val handler: MethodCallHandler =
        MethodCallHandler { call, result ->
            when (call.method) {
                "foo" -> {
                    scope.launch {
                        val fooResult = commonRepository.foo()
                        result.success(fooResult)
                    }
                }
            }
        }

    init {
        Log.d("JQN", ": FlutterChannel")
        MethodChannel(messenger, "com.example.testfluttermodel/channel").apply {
            setMethodCallHandler(handler)
        }
    }
}