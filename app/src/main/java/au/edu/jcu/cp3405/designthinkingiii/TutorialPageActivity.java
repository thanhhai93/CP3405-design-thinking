package au.edu.jcu.cp3405.designthinkingiii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class TutorialPageActivity extends AppCompatActivity {

    ImageView btnContactTut, btnMessageTut, btnVideoCallTut, btnVoiceRecognitionTut, btnVoiceCallTut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_page);

        btnContactTut= findViewById(R.id.contactTutorialImage);

        btnContactTut.setOnClickListener(v ->{
            startActivity(new Intent(TutorialPageActivity.this, TutorialContactActivity.class));
        });

        btnMessageTut = findViewById(R.id.messageTutorialImage);

        btnMessageTut.setOnClickListener(v ->{
            startActivity(new Intent(TutorialPageActivity.this, TutorialMessageActivity.class));
        });

        btnVideoCallTut= findViewById(R.id.videoCallTutorialImage);
        btnVideoCallTut.setOnClickListener(v ->{
            startActivity(new Intent(TutorialPageActivity.this, TutorialVideoCallActivity.class));
        });

        btnVoiceRecognitionTut= findViewById(R.id.voiceRegTutorialImage);
        btnVoiceRecognitionTut.setOnClickListener(v->{
            startActivity(new Intent(TutorialPageActivity.this, TutorialVoiceRecognitionActivity.class));
        });

        btnVoiceCallTut= findViewById(R.id.voiceCallTutorialImage);
        btnVoiceCallTut.setOnClickListener(v->{
            startActivity(new Intent(TutorialPageActivity.this, TutorialVoiceCallActivity.class));
        });







    }
}