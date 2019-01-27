package uk.com.holinight;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

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
  private ImageView partyImage;
  private List<eventObject> eventObjectList = new ArrayList<>();
  private int imagePosition = 0;
  private TextView location;
  private TextView description;
  String facebookUserId = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_join_party);
    partyImage = (ImageView) findViewById(R.id.partyImage);
    partyImage.setImageResource(R.drawable.empty);
    description = (TextView) findViewById(R.id.description);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    location = (TextView) findViewById(R.id.location);

    new getEvents(this).execute();
    final Context context = this;

    partyImage.setOnTouchListener(
        new OnSwipeTouchListener(this) {
          @Override
          public void onSwipeRight() {
            Log.d(TAG, "Swipe Right");
            if (imagePosition < eventObjectList.size() - 1) {
              Picasso.with(context)
                  .load(
                      ApplicationConstants.BASE_URL
                          + eventObjectList.get(imagePosition + 1).getEventImageURL())
                  .into(partyImage);
              location.setText(eventObjectList.get(imagePosition + 1).getLocation());
              description.setText(eventObjectList.get(imagePosition + 1).getEventDescription());
              Log.d(TAG, eventObjectList.get(imagePosition + 1).getEventImageURL());
              imagePosition++;
            } else {
              partyImage.setImageResource(R.drawable.empty);
              location.setText("");
              description.setText("");

            }
          }

          @Override
          public void onSwipeLeft() {
            Log.d(TAG, "Swipe Left");

            if (imagePosition < eventObjectList.size() - 1) {

              Picasso.with(context)
                  .load(
                      ApplicationConstants.BASE_URL
                          + eventObjectList.get(imagePosition + 1).getEventImageURL())
                  .into(partyImage);
              location.setText(eventObjectList.get(imagePosition + 1).getLocation());
              description.setText(eventObjectList.get(imagePosition + 1).getEventDescription());

              Log.d(TAG, eventObjectList.get(imagePosition + 1).getEventImageURL());

              imagePosition++;

            } else {
              partyImage.setImageResource(R.drawable.empty);
              location.setText("");
              description.setText("");
            }
          }
        });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  private class getEvents extends AsyncTask<String, Void, String> {
    private Context mContext;

    public getEvents(Context context) {
      mContext = context;
    }

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
            Toast.makeText(JoinParty.this, "No events requests available!", Toast.LENGTH_LONG)
                .show();
          } else {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(JoinParty.this);
            Log.d("input", items.toString());
            JSONArray jsonArray = items.getJSONArray("events");

            Log.d("Array", "jsonArray size: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
              JSONObject eventObjectFromServer = jsonArray.getJSONObject(i);
              Log.d(TAG, eventObjectFromServer.toString());

              eventObjectList.add(new eventObject(eventObjectFromServer));
            }
            Log.d(TAG, eventObjectList.get(imagePosition).getEventImageURL());

            Picasso.with(mContext)
                .load(
                    ApplicationConstants.BASE_URL
                        + eventObjectList.get(imagePosition).getEventImageURL())
                .into(partyImage);
            location.setText(eventObjectList.get(imagePosition).getLocation());
            description.setText(eventObjectList.get(imagePosition).getEventDescription());

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

      // SharedPreferences prefs = PreferenceManager
      // .getDefaultSharedPreferences(RecentSignals.this);
      try {

        HttpGet httpGet = new HttpGet(ApplicationConstants.BASE_URL + urltext);
        Log.d("u2", ApplicationConstants.BASE_URL + urltext);
        HttpResponse response = HttpConnectionManager.getClient().execute(httpGet);

        InputStream responseInputStream = response.getEntity().getContent();
        BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(responseInputStream));

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
