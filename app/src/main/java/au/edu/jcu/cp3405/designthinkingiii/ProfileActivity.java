package au.edu.jcu.cp3405.designthinkingiii;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {

    private static final String TAG ="TAG" ;
    EditText mFullName, mEmail;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    Button mProfileButton, backHomeBtn;
    TextView mUserPhone;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFullName = findViewById(R.id.nameInput);
        mEmail = findViewById(R.id.inputEmail);
        mAuth = FirebaseAuth.getInstance();

        fStore = FirebaseFirestore.getInstance();
        mProfileButton = findViewById(R.id.saveProfileBtn);
        mUserPhone = findViewById(R.id.text_phone);
        backHomeBtn = findViewById(R.id.backHomeBtn);

        FirebaseUser currentUser = mAuth.getCurrentUser();


        assert currentUser != null;
        String phoneNumber = currentUser.getPhoneNumber();
        String inform = String.format("Your phone number is: %s", phoneNumber);
        mUserPhone.setText(inform);

        backHomeBtn.setOnClickListener(v->{
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });

        mProfileButton.setOnClickListener(v -> {
             final String email = mEmail.getText().toString().trim();
             final String name = mFullName.getText().toString().trim();

            String phone = currentUser.getPhoneNumber();

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is Required!");
            }
            if (TextUtils.isEmpty(name)) {
                mFullName.setError("Name is required!");
            }
            Toast.makeText(ProfileActivity.this, "Profile saved", Toast.LENGTH_SHORT).show();
            UserID = mAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fStore.collection("users").document(UserID);
            Map<String, Object> user = new HashMap<>();
            user.put("name", name);
            user.put("email", email);
            user.put("phone", phone);
            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d(TAG, "onSuccess: user profile is saved at: "+ UserID);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.toString());
                }
            });



            startActivity(new Intent(getApplicationContext(), ProfileDisplayActivity.class));





        });




    }
}