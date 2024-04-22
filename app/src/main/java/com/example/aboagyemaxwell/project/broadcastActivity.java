package com.example.aboagyemaxwell.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class broadcastActivity extends AppCompatActivity {
    private EditText tittle,message;
    private Button send_message;
    DatabaseReference databaseReference;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        getSupportActionBar().setTitle("Broadcast Information");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tittle = findViewById(R.id.tittle);
        message = findViewById(R.id.b_message);
        send_message = findViewById(R.id.send_message);
        progressBar = findViewById(R.id.progressBar);


        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(tittle.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Title field can't be empty", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(message.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Message field cant be empty", Toast.LENGTH_SHORT).show();
                }else {
                    progressBar.setVisibility(View.VISIBLE);

                    broadcast information = new broadcast(tittle.getText().toString(),message.getText().toString());

                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("broadcast_personnel");

                    myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"broadcast Sent",Toast.LENGTH_SHORT).show();
                                tittle.getText().clear();
                                message.getText().clear();

                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"broadcast failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }


            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == item.getItemId()){
            //end Activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
