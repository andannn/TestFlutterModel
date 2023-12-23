package com.example.testfluttermodel

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.testfluttermodel.databinding.FlutterFragmentContainerLayoutBinding
import io.flutter.embedding.android.FlutterFragment

private const val TAG = "MyFlutterFragment"

class FlutterContainerFragment : Fragment() {

    private val args by navArgs<FlutterContainerFragmentArgs>()

    companion object {
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment_tag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FlutterFragmentContainerLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // make flutter fragment full screen.
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // handle back key in flutter engine.
        val flutterFragment = FlutterFragment.NewEngineFragmentBuilder().apply {
            shouldAutomaticallyHandleOnBackPressed(true)
            dartEntrypointArgs(listOf(args.flutterScreenName))
        }.build<FlutterFragment>()

        childFragmentManager
            .beginTransaction()
            .add(
                R.id.flutter_container,
                flutterFragment,
                TAG_FLUTTER_FRAGMENT
            )
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().window.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}