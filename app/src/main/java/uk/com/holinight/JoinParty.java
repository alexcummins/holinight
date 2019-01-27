package uk.com.holinight;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class JoinParty extends AppCompatActivity {
private final String TAG = "JoinParty";
private  ImageView partyImage;
private List<eventObject> eventObjectList = new ArrayList<>();
    String facebookUserId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_party);
        partyImage = (ImageView) findViewById(R.id.partyImage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        new getEvents().execute();


        partyImage.setOnTouchListener(new OnSwipeTouchListener(this){
            public void onSwipeRight() {
                partyImage.setImageResource(R.drawable.picture1);
                Log.d(TAG, "Swipe Right");
            }
            public void onSwipeLeft() {


                partyImage.setImageResource(R.drawable.picture2);

                Log.d(TAG, "Swipe Left");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        new getEvents();
    }



    private class getEvents extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            Log.d(TAG, result);
            if (result != null) {

                try {

                    JSONObject items = new JSONObject(result);
                    int value = items.getInt("value");

                    if (value == 0) {
                        Toast.makeText(JoinParty.this,
                                "No events requests available!", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences prefs = PreferenceManager
                                .getDefaultSharedPreferences(JoinParty.this);
                        Log.d("input" , items.toString());
                        JSONArray jsonArray = items.getJSONArray("events");

                        Log.d("Array", "jsonArray size: " + jsonArray.length() );
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject eventObjectFromServer = jsonArray.getJSONObject(i);
                            Log.d(TAG, eventObjectFromServer.toString());


                            eventObjectList.add( new eventObject(eventObjectFromServer));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            StringBuilder responseStr = new StringBuilder();

            String urltext;

                urltext = "getEvents.php";

            //SharedPreferences prefs = PreferenceManager
            //.getDefaultSharedPreferences(RecentSignals.this);
            try {


                HttpGet httpGet = new HttpGet(
                        ApplicationConstants.BASE_URL+urltext);
                Log.d("u2", ApplicationConstants.BASE_URL+urltext);
                HttpResponse response = HttpConnectionManager.getClient()
                        .execute(httpGet);

                InputStream responseInputStream = response.getEntity()
                        .getContent();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(responseInputStream));

                String responseLineStr = null;
                while ((responseLineStr = bufferedReader.readLine()) != null) {
                    responseStr.append(responseLineStr);

                }
                 Log.d("responce: ", responseStr.toString());

                bufferedReader.close();
                responseInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseStr.toString();
        }

    }


    }


