package au.edu.jcu.cp3405.designthinkingiii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button mLogOutBtn;
    private FirebaseAuth mAuth;
    private ImageView videoCallButton;
    private TextView mUserPhone;
    private String phoneNumber;
    private ImageView message_boxButton;
    private ImageView VoiceCallButton;
    private ImageView ContactsButton;
    private ImageView btnvoicerecognition;
    private ImageView btnTutorialPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogOutBtn = findViewById(R.id.log_out_btn);
        videoCallButton= findViewById(R.id.videocall);
        mAuth = FirebaseAuth.getInstance();
        mUserPhone = findViewById(R.id.text_phone);
        message_boxButton = findViewById(R.id.message_box);
        VoiceCallButton = findViewById(R.id.voicecall);
        ContactsButton = findViewById(R.id.contacts);
        btnvoicerecognition = findViewById(R.id.btnvoicerecognition);
        btnTutorialPage = findViewById(R.id.tutorialPage);



        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String phoneNumber = currentUser.getPhoneNumber();
        String inform = String.format("Your phone number is: %s", phoneNumber);

        mLogOutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        videoCallButton.setOnClickListener( v -> {
            startActivity(new Intent(MainActivity.this, VideoCallMainActivity.class));
        });

        mUserPhone.setText(inform);

        message_boxButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MessageActivity.class));
                });

        VoiceCallButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VoiceCallActivity.class));
        });
        ContactsButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,ContactProfileActivity.class));
        });
        btnvoicerecognition.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, VoiceRecognitionActivity.class));
        });

        mUserPhone.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileDisplayActivity.class));
        });

        btnTutorialPage.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, TutorialPageActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}