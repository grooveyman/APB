package com.example.aboagyemaxwell.project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomSheetDialog bottomSheetDialog;
    private long backPressedTime;
    private Toast backToast;
    private WebView view;
    private String url = "https://www.myjoyonline.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.dialog_layout,null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        view =  findViewById(R.id.webview);

        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLoadWithOverviewMode(true);
        view.getSettings().setUseWideViewPort(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setPluginState(WebSettings.PluginState.ON);
        view.setWebViewClient(new WebViewClient());

        view.loadUrl(url);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            return true;
            Toast.makeText(getApplicationContext(),"login item clicked",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void broadcast(View view){
        startActivity(new Intent(getApplicationContext(),broadcastActivity.class));
    }

    public void alerts(View view){
        startActivity(new Intent(getApplicationContext(),alertsActivity.class));
    }

    public void logout(View view){
        Toast.makeText(getApplicationContext(),"logout clicked",Toast.LENGTH_SHORT).show();
    }

    public void news_tips(){
        startActivity(new Intent(getApplicationContext(),newsTipsActivity.class));
    }

    public void onBackPressed() {

        if(backPressedTime+2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
            backToast = Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}
