package com.example.testfluttermodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testfluttermodel.databinding.FragmentPagerListBinding
import com.example.testfluttermodel.databinding.FragmentSecondBinding

private const val TAG = "SecondFragment"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private var mAdapter: MyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d(TAG, "onCreateView: $savedInstanceState ${this.hashCode()}")
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        mAdapter = MyAdapter(childFragmentManager, lifecycle)

        binding.pager.setAdapter(mAdapter)

        binding.gotoFirst.setOnClickListener {
            binding.pager.currentItem = 0
        }
        binding.gotoLast.setOnClickListener {
            binding.pager.currentItem = NUM_ITEMS - 1
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        requireActivity().window.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

const val NUM_ITEMS = 10

class MyAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    override fun createFragment(position: Int): Fragment {
        return ArrayListFragment.newInstance(position)
    }
}

class ArrayListFragment : ListFragment() {
    var mNum: Int = 0

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mNum = arguments?.getInt("num") ?: -1
    }

    /**
     * The Fragment's UI is just a simple text view showing its
     * instance number.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentPagerListBinding.inflate(inflater, container, false)
        binding.text.text = "Fragment #$mNum"
        return binding.root
    }

    companion object {
        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        fun newInstance(num: Int): ArrayListFragment {
            val f = ArrayListFragment()

            // Supply num input as an argument.
            val args =
                Bundle().apply {
                    putInt("num", num)
                }
            f.setArguments(args)

            return f
        }
    }
}
