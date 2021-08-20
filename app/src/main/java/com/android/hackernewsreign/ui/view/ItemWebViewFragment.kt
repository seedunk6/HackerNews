package com.android.hackernewsreign.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.hackernewsreign.databinding.FragmentItemwebviewBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.view.isVisible
import com.android.hackernewsreign.R
import com.android.hackernewsreign.utils.toast


@AndroidEntryPoint
class ItemWebViewFragment : Fragment() {

    companion object {
        private const val URL = "url"
    }

    private var _binding: FragmentItemwebviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemwebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webViewItem.settings.javaScriptEnabled = true

        binding.webViewItem.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                binding.progressBar.isVisible = false
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String,
                                         failingUrl: String
            ) {
                context?.toast(resources.getString(R.string.page_not_available))
            }
        }


        binding.webViewItem.loadUrl(url)

    }
}