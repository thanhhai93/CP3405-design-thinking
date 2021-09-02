package au.edu.jcu.cp3405.designthinkingiii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class ProfileDisplayActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    Button mProfileEditBtn, backHomeBtn;
    TextView nameDisplay, emailDisplay, mUserPhone;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);

        mProfileEditBtn = findViewById(R.id.EditProfileBtn);
        nameDisplay= findViewById(R.id.name_display);
        emailDisplay = findViewById(R.id.email_display);
        mAuth = FirebaseAuth.getInstance();
        mUserPhone = findViewById(R.id.text_phone);
        backHomeBtn = findViewById(R.id.backHomeBtn);

        fStore = FirebaseFirestore.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        assert currentUser != null;
        String userID = currentUser.getUid();
        String phoneNumber = currentUser.getPhoneNumber();
        String inform = String.format("Your phone number is: %s", phoneNumber);
        mUserPhone.setText(inform);

        DocumentReference profileRef = fStore.collection("users").document(userID);

        profileRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String name = documentSnapshot.getString("name");
                            String email = documentSnapshot.getString("email");

                             // Map<String, Object> note = documentSnapshot.getData();
                            nameDisplay.setText("Your Name is: "+ name);
                            emailDisplay.setText("Your Email is: "+ email);

                        } else{
                            Toast.makeText(ProfileDisplayActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileDisplayActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure:" + e.toString());
                    }
                });


        mProfileEditBtn.setOnClickListener(v -> {
            startActivity(new Intent(ProfileDisplayActivity.this, ProfileActivity.class));
        });
        backHomeBtn.setOnClickListener(v->{
            startActivity(new Intent(ProfileDisplayActivity.this, MainActivity.class));
        });

    }
}