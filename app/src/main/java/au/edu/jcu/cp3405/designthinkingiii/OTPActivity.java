package au.edu.jcu.cp3405.designthinkingiii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class    OTPActivity extends AppCompatActivity {

    private Button mVerifyCodeBtn;
    private EditText otpEdit;
    private String OTP;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mVerifyCodeBtn = findViewById(R.id.verifycode_btn);
        otpEdit = findViewById(R.id.verify_code_edit);

        firebaseAuth = FirebaseAuth.getInstance();

        OTP = getIntent().getStringExtra("auth");
        mVerifyCodeBtn.setOnClickListener(v -> {
            String verification_code = otpEdit.getText().toString();
            if(!verification_code.isEmpty()){
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP , verification_code);
                signIn(credential);
            }else{
                Toast.makeText(OTPActivity.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void signIn(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                sendToMain();
            }else{
                Toast.makeText(OTPActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser !=null){
            sendToMain();
        }
    }

    private void sendToMain(){
        startActivity(new Intent(OTPActivity.this , MainActivity.class));
        finish();
    }
}