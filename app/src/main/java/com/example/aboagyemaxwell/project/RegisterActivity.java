package com.example.aboagyemaxwell.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText email,password,retype_password,username,full_name,ref_number,index_number,alert_phrase;
    private ProgressBar progressBar;
    private Button register;
    private TextView already_account;
    private RadioButton male,female;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.regis_email);
        password = findViewById(R.id.regis_password);
        register = findViewById(R.id.register_btn);
        already_account = findViewById(R.id.already_account);
        retype_password = findViewById(R.id.regis_retype_password);
        username = findViewById(R.id.regis_username);
        full_name = findViewById(R.id.full_name);
        ref_number = findViewById(R.id.ref_num);
        index_number = findViewById(R.id.index_num);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        progressBar = findViewById(R.id.regis_progressBar);
        alert_phrase = findViewById(R.id.alert_word);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_retype_password = retype_password.getText().toString();
                final String txt_username = username.getText().toString();
                final String txt_full_name = full_name.getText().toString();
                final String txt_ref_number = ref_number.getText().toString();
                final String txt_index_number = index_number.getText().toString();
                final String txt_status = "Personnel";
                final String txt_key_phrase = alert_phrase.getText().toString();



                if(male.isChecked()){
                    gender = "Male";
                }
                if(female.isChecked()){
                    gender = "Female";
                }

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password) || TextUtils.isEmpty(txt_retype_password)||TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_full_name)||TextUtils.isEmpty(txt_ref_number)||TextUtils.isEmpty(txt_index_number)||TextUtils.isEmpty(gender)){
                    Toast.makeText(RegisterActivity.this,"Enter Credentials",Toast.LENGTH_SHORT).show();
                }else if (txt_password.length()<6 ||txt_retype_password.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
                }else if (txt_password.equals(txt_retype_password) == false){
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);

                    firebaseAuth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                user information = new user(txt_email,txt_username,txt_full_name,txt_ref_number,txt_index_number,txt_status,txt_key_phrase,gender);

                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        finish();

                                    }
                                });
                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });


                }

            }

        });

        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

    }
}
