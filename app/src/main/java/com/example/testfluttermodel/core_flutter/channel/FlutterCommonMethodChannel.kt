package com.example.testfluttermodel.core_flutter.channel

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.testfluttermodel.R
import com.example.testfluttermodel.SecondFragmentDirections
import com.example.testfluttermodel.core_data.CommonRepository
import com.example.testfluttermodel.feature_login.FlutterLoginADirections
import com.example.testfluttermodel.feature_login.FlutterLoginBDirections
import com.example.testfluttermodel.feature_screenA.FlutterScreenADirections
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface FlutterMethodHandler {
    suspend fun someCallToFlutter(input: Int): String
}

private const val TAG = "FlutterCommonMethodChan"

class FlutterCommonMethodChannel(
    messenger: BinaryMessenger,
    scope: CoroutineScope,
    private val navController: NavController,
    private val commonRepository: CommonRepository
) : FlutterMethodHandler {

    private val methodChannel: MethodChannel

    private val handler = MethodCallHandler { call, result ->
        Log.d(TAG, ": MethodCallHandler call $call")
        when (call.method) {
            "foo" -> {
                scope.launch {
                    val fooResult = commonRepository.foo()
                    result.success(fooResult)
                }
            }

            "navigateToScreenB" -> {
                val direction =
                    FlutterScreenADirections.actionFlutterScreenAToFlutterScreenB()
                navController.navigate(direction)
            }

            "navigateToLoginB" -> {
                val direction =
                    FlutterLoginADirections.actionFlutterLoginAToFlutterLoginB()
                navController.navigate(direction)
            }

            "navigateToMainFlow" -> {
                val direction =
                    FlutterLoginBDirections.actionLoginToFirstFragment()
                navController.navigate(
                    direction,
                    navOptions {
                        popUpTo(R.id.nav_graph)
                    },
                )
            }
        }
    }

    init {
        methodChannel = MethodChannel(messenger, "com.example.testfluttermodel/channel").apply {
            setMethodCallHandler(handler)
        }
    }

    override suspend fun someCallToFlutter(input: Int): String {
        return callFlutter(
            method = "someCallToFlutter",
            arguments = mapOf("input" to input)
        )
    }

    private suspend fun <T> callFlutter(method: String, arguments: Any): T =
        suspendCancellableCoroutine { continuation ->
            val callBack = object : MethodChannel.Result {
                override fun success(result: Any?) {
                    try {
                        continuation.resume(result as T)
                    } catch (e: Exception) {
                        continuation.resumeWithException(IllegalArgumentException())
                    }
                }

                override fun error(errorCode: String, errorMessage: String?, errorDetails: Any?) {
                    continuation.resumeWithException(IllegalStateException())
                }

                override fun notImplemented() {
                    continuation.resumeWithException(NotImplementedError())
                }
            }

            methodChannel.invokeMethod(
                /* method = */ method,
                /* arguments = */ arguments,
                /* callback = */ callBack,
            )
        }
}