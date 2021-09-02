package au.edu.jcu.cp3406.designthinkingiii

import android.webkit.JavascriptInterface

class JavascriptInterface (val callActivity: CallActivity) {

    @JavascriptInterface
    public fun onPeerConnected() {
        callActivity.onPeerConnected()
    }
}