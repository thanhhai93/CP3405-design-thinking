package au.edu.jcu.cp3405.designthinkingiii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceRecognitionActivity extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private TextView ResponseText;
    private ImageButton mSpeakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);

        mSpeakBtn.setOnClickListener(v -> startVoiceInput());


    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                }
                if (mVoiceInputTv.getText().toString().equals("contact") || mVoiceInputTv.getText().toString().equals("open contact") ||  mVoiceInputTv.getText().toString().equals("contacts") || mVoiceInputTv.getText().toString().equals("open contacts")){
                    Intent startContact = new Intent(getApplicationContext(), ContactProfileActivity.class);

                    startActivity(startContact);
                }
                if (mVoiceInputTv.getText().toString().equals("voice call") || mVoiceInputTv.getText().toString().equals("open voice call") ||  mVoiceInputTv.getText().toString().equals("voice calls") || mVoiceInputTv.getText().toString().equals("open voice calls")){
                    Intent startVoice = new Intent(getApplicationContext(), VoiceCallActivity.class);

                    startActivity(startVoice);
                }
                if (mVoiceInputTv.getText().toString().equals("video call") || mVoiceInputTv.getText().toString().equals("open video call") ||  mVoiceInputTv.getText().toString().equals("video calls") || mVoiceInputTv.getText().toString().equals("open video calls")){
                    Intent startVideo = new Intent(getApplicationContext(), VideoCallMainActivity.class);

                    startActivity(startVideo);
                }
                if (mVoiceInputTv.getText().toString().equals("message") || mVoiceInputTv.getText().toString().equals("open message") ||  mVoiceInputTv.getText().toString().equals("messages") || mVoiceInputTv.getText().toString().equals("open messages")){
                    Intent startVideo = new Intent(getApplicationContext(), MessageActivity.class);

                    startActivity(startVideo);
                }
                if (mVoiceInputTv.getText().toString().equals("make new contact") || mVoiceInputTv.getText().toString().equals("create new contact") ||  mVoiceInputTv.getText().toString().equals("add contact") || mVoiceInputTv.getText().toString().equals("new contact") || mVoiceInputTv.getText().toString().equals("add new contact")){
                    Intent startVideo = new Intent(getApplicationContext(), ContactProfileEditorActivity.class);

                    startActivity(startVideo);

                }
                if (mVoiceInputTv.getText().toString().equals("weather") || mVoiceInputTv.getText().toString().equals("weather today") ||  mVoiceInputTv.getText().toString().equals("how is the weather today") || mVoiceInputTv.getText().toString().equals("temperature") || mVoiceInputTv.getText().toString().equals("what is the temperature outside") || mVoiceInputTv.getText().toString().equals("how's the weather today")){
                    Intent weatherIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nea.gov.sg/weather"));
                    startActivity(weatherIntent);

                }
                if (mVoiceInputTv.getText().toString().equals("newspaper") || mVoiceInputTv.getText().toString().equals("latest news") ||  mVoiceInputTv.getText().toString().equals("today's newspaper") || mVoiceInputTv.getText().toString().equals("todays news") || mVoiceInputTv.getText().toString().equals("news of today") || mVoiceInputTv.getText().toString().equals("open weather")){
                    Intent newsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.straitstimes.com/"));
                    startActivity(newsIntent);

                }
                if (mVoiceInputTv.getText().toString().equals("YouTube") || mVoiceInputTv.getText().toString().equals("open YouTube") ||  mVoiceInputTv.getText().toString().equals("video clips") ){
                    Intent newsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"));
                    startActivity(newsIntent);

                }
                if (mVoiceInputTv.getText().toString().equals("Google") || mVoiceInputTv.getText().toString().equals("open Google") ||  mVoiceInputTv.getText().toString().equals("information") || mVoiceInputTv.getText().toString().equals("search engine") ){
                    Intent newsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                    startActivity(newsIntent);

                }
                if (mVoiceInputTv.getText().toString().equals("map") || mVoiceInputTv.getText().toString().equals("open map") ||  mVoiceInputTv.getText().toString().equals("map direction") || mVoiceInputTv.getText().toString().equals("world map") ){
                    Intent newsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps"));
                    startActivity(newsIntent);

                }


                else{
                    Toast.makeText(VoiceRecognitionActivity.this, "Sorry, we could not proceed that action right now! But we will update shortly", Toast.LENGTH_SHORT ).show();
                }
            }

        }
    }

}