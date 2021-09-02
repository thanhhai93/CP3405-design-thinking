package au.edu.jcu.cp3406.designthinkingiii

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_video_call_main.*


class VideoCallMainActivity : AppCompatActivity() {

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    private val requestcode = 1
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_call_main)

        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth!!.currentUser
        val phoneNumber = currentUser?.phoneNumber


        if (!isPermissionGranted()) {
            askPermissions()
        }

        Firebase.initialize(this)
        getStartBtn.setOnClickListener{
            val intent = Intent(this, CallActivity::class.java)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        }




    }
    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, permissions, requestcode)
    }
    private fun isPermissionGranted(): Boolean {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }
}