package de.daywalk3r666.forms

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)
        setCookies(webView)
        setupWebView(webView)
        loadWebsite(webView)
    }


    private fun setupWebView(webView: WebView) {
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.supportMultipleWindows()
    }


    private fun loadWebsite(webView: WebView) {
        val domain = "https://docs.google.com/forms/d/1gw4v6d_iTVsDiFAvvJBrzKgOgr6DlnuyNNYZOYbSd_c/viewform?edit_requested=true&edit_requested=true#responses"

        webView.webViewClient = WebViewClient()
        webView.loadUrl(domain)
    }


    private fun setCookies(webView: WebView) {
        val cookieManager = CookieManager.getInstance()

        cookieManager.acceptCookie()
        cookieManager.setAcceptCookie(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        }
    }


    private fun previousWebpage() {
        val webView: WebView = findViewById(R.id.webview)

        if (webView.canGoBack()) {
            webView.goBack()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    previousWebpage()
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}