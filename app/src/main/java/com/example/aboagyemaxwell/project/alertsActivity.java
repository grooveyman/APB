package com.example.aboagyemaxwell.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class alertsActivity extends AppCompatActivity implements MyAdapter.onItemClickListener{
    public static final String EXTRA_USERNAME = "username";
    public static final String EXTRA_FULLNAME = "name";
    public static final String EXTRA_REF_NUM = "ref";
    public static final String EXTRA_GENDER = "gen";
    public static final String EXTRA_USER_UID = "user_uid";

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<message> list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        getSupportActionBar().setTitle("Alerts");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        recyclerView = findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<message>();

        reference = FirebaseDatabase.getInstance().getReference().child("alerts");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    message p = dataSnapshot1.getValue(message.class);
                    list.add(p);
                }
                adapter = new MyAdapter(getApplicationContext(),list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(alertsActivity.this);

                //send notification to students for new message received from personnel
                String messag ="This is a notification example";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.alert_notification_ic)
                        .setContentTitle("New notification")
                        .setContentText(messag)
                        .setAutoCancel(true);

                Intent intent = new Intent(getApplicationContext(),alertsActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);

                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Opss something went wrong",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(getApplicationContext(),mapActivity.class);
        message message = list.get(position);
        detailIntent.putExtra(EXTRA_USERNAME,message.getUsername());
        detailIntent.putExtra(EXTRA_FULLNAME,message.getFull_name());
        detailIntent.putExtra(EXTRA_REF_NUM,message.getRef_number());
        detailIntent.putExtra(EXTRA_GENDER,message.getGender());
        detailIntent.putExtra(EXTRA_USER_UID,message.getUser_uid());

        startActivity(detailIntent);
    }
}
