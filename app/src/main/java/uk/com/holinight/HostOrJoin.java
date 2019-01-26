package uk.com.holinight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HostOrJoin extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_or_join);

//        button = (Button) findViewById(R.id.button);
//        sendToHostActivity(button);
    }

    public void sendToHostActivity(View v) {

    }
    public void sendToJoinActivity(View v) {
        Intent i = new Intent(this, JoinParty.class);
        startActivity(i);
    }
}
