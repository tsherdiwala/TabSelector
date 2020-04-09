package com.tejas.tabselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContentFragment : Fragment() {

    companion object {

        private val TAG = ContentFragment::class.java.simpleName
        private val ARGS_CONTENT = "$TAG.ARGS_CONTENT"

        fun newInstance(content: String): ContentFragment = ContentFragment().apply {
            arguments = Bundle().apply {
                putString(ARGS_CONTENT, content)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_content, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        view.findViewById<TextView>(R.id.contentTV).text =
            arguments?.getString(ARGS_CONTENT) ?: "No content"
    }

}