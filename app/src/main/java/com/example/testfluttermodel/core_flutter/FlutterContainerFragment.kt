package com.example.testfluttermodel.core_flutter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.testfluttermodel.core_data.CommonRepositoryImpl
import com.example.testfluttermodel.R
import com.example.testfluttermodel.core_flutter.channel.FlutterCommonMethodChannel
import com.example.testfluttermodel.core_flutter.channel.FlutterMethodHandler
import com.example.testfluttermodel.databinding.FlutterFragmentContainerLayoutBinding
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

private val TAG = FlutterContainerFragment::class.java.simpleName

abstract class FlutterContainerFragment : Fragment() {

    private val flutterFragment: FlutterFragment
        get() =
            childFragmentManager.findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment

    abstract val flutterScreenName: String

    protected var flutterMethodHandler: FlutterMethodHandler? = null

    companion object {
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment_tag"
    }

    private val fragmentLifecycleCallback = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            if (flutterFragment === f) {
                flutterFragment.flutterEngine?.let { engine ->
                    Log.d(TAG, "onFragmentAttached: engine? $engine")

                    flutterMethodHandler = FlutterCommonMethodChannel(
                        messenger = engine.dartExecutor,
                        scope = lifecycleScope,
                        commonRepository = CommonRepositoryImpl()
                    )
                }
            }
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            if (flutterFragment === f) {
                flutterMethodHandler = null
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        val binding = FlutterFragmentContainerLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if (savedInstanceState == null) {
            val flutterFragment =
                FlutterFragment.NewEngineInGroupFragmentBuilder(
                    /* fragmentClass = */ CustomFlutterFragment::class.java,
                    /* engineGroupId = */ "flutter_engine_group"
                ).apply {
                    // handle back key in flutter engine.
                    shouldAutomaticallyHandleOnBackPressed(true)
                }.build<CustomFlutterFragment>()

            childFragmentManager
                .beginTransaction()
                .add(
                    R.id.flutter_container,
                    flutterFragment,
                    TAG_FLUTTER_FRAGMENT
                )
                .commit()

            childFragmentManager.registerFragmentLifecycleCallbacks(
                /* cb = */ this.fragmentLifecycleCallback,
                /* recursive = */ false
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")

        childFragmentManager.unregisterFragmentLifecycleCallbacks(this.fragmentLifecycleCallback)

        if (!requireActivity().isChangingConfigurations) {
            requireActivity().window.clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}