package uk.com.holinight;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class HostParty extends AppCompatActivity {

  private EditText descriptionField;
  private TimePicker timePicker;
  private EditText time;
  private Calendar calendar;
  private String format;
  private ProgressDialog progress;
  private ImageButton imagebutton;
  private EditText nameField;
  private EditText locationField;
  private String urltext;
  private EditText maxRaduisField;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_host_party);
    nameField = (EditText) findViewById(R.id.eventName);
    locationField = (EditText) findViewById(R.id.location);
    descriptionField = (EditText) findViewById(R.id.description);
    maxRaduisField = (EditText) findViewById(R.id.maxRadius);
    time = (EditText) findViewById(R.id.timeField);
    calendar = Calendar.getInstance();
    imagebutton = (ImageButton) findViewById(R.id.partyImage);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    showTime(hour, min);
  }

  public void setTime(View view) {
    int hour = timePicker.getCurrentHour();
    int min = timePicker.getCurrentMinute();
    showTime(hour, min);
  }

  public void showTime(int hour, int min) {
    if (hour == 0) {
      hour += 12;
      format = "AM";
    } else if (hour == 12) {
      format = "PM";
    } else if (hour > 12) {
      hour -= 12;
      format = "PM";
    } else {
      format = "AM";
    }

    time.setText(
        new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
  }

  public void timeButton(View view) {
    timePicker.setVisibility(View.VISIBLE);
  }

  public void changeImage() {
    imagebutton.setImageResource(R.drawable.op);
  }

  public void submitButton(View view) {

    urltext =
        "addEvent.php?eventName="
            + nameField.getText()
            + "&location="
            + locationField.getText()
            + "&eventDescription="
            + descriptionField.getText()
            + "&maxRadius="
            + maxRaduisField.getText()
//            + "&startTime="
//            + time.getText()
            + "&userID="
            + FirebaseAuth.getInstance().getUid();
    new addEvents(this).execute();
  }

  public void imageFind(View view) {
    progress = new ProgressDialog(this);
    progress.setTitle("Please wait!");
    progress.setMessage(
        "Please wait while our AI searches your device for the optimal event picture!");
    progress.setCancelable(false);
    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progress.show();
    final Timer t = new Timer();
    t.schedule(
        new TimerTask() {
          public void run() {
            progress.dismiss(); // when the task active then close the dialog

            runOnUiThread(
                new Runnable() {

                  @Override
                  public void run() {

                    changeImage();
                  }
                });
            t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
          }
        },
        5000); // after 2 second (or 2000 miliseconds), the task will be active.
  }

  private class addEvents extends AsyncTask<String, Void, String> {
    private Context mContext;

    addEvents(Context context) {
      mContext = context;
    }

    @Override
    protected void onPostExecute(String result) {
      // TODO Auto-generated method stub
      super.onPostExecute(result);
      Intent i = new Intent(mContext, HostOrJoin.class);
      startActivity(i);
    }

    @Override
    protected String doInBackground(String... params) {
      // TODO Auto-generated method stub
      StringBuilder responseStr = new StringBuilder();

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
