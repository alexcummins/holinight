package uk.com.holinight;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {
  private FirebaseAuth mAuth;
  private EditText emailField;
  private EditText passwordField;
  private ImageView logo;
  private static String TAG = "Login Activity";
  CallbackManager mCallbackManager;

  @Override
  public void onStart() {
    super.onStart();

    //         Check if user is signed in (non-null) and update UI accordingly.
    FirebaseUser currentUser = mAuth.getCurrentUser();
    if (currentUser != null) {
      updateUI(currentUser);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mAuth = FirebaseAuth.getInstance();
    FacebookSdk.sdkInitialize(getApplicationContext());
    setContentView(R.layout.activity_login);
    AppEventsLogger.activateApp(this);
    emailField = (EditText) findViewById(R.id.email_text);
    passwordField = (EditText) findViewById(R.id.password_text);
    logo = findViewById(R.id.logo);
    logo.setOnTouchListener(new OnSwipeTouchListener(this));

    mCallbackManager = CallbackManager.Factory.create();
    LoginButton loginButton = findViewById(R.id.buttonFacebookLogin);
    loginButton.setReadPermissions("email", "public_profile");
    loginButton.registerCallback(
        mCallbackManager,
        new FacebookCallback<LoginResult>() {
          @Override
          public void onSuccess(LoginResult loginResult) {
            Log.d(TAG, "facebook:onSuccess:" + loginResult);
            handleFacebookAccessToken(loginResult.getAccessToken());
          }

          @Override
          public void onCancel() {
            Log.d(TAG, "facebook:onCancel");
            // ...
          }

          @Override
          public void onError(FacebookException error) {
            Log.d(TAG, "facebook:onError", error);
            // ...
          }
        });
  }

  public void loginButton(View v) {

    String email = "blank";
    email = emailField.getText().toString();
    String password = "blank";

    password = passwordField.getText().toString();
    // verifyLogin(email, password);
    mAuth
        .signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(
            this,
            new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // Sign in success, update UI with the signed-in user's information
                  Log.d(TAG, "signInWithEmail:success");
                  FirebaseUser user = mAuth.getCurrentUser();
                  updateUI(user);
                } else {
                  // If sign in fails, display a message to the user.
                  Log.w(TAG, "signInWithEmail:failure", task.getException());
                  Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT)
                      .show();
                  updateUI(null);
                }

                // ...
              }
            });
  }

  // ...

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // Pass the activity result back to the Facebook SDK
    mCallbackManager.onActivityResult(requestCode, resultCode, data);
  }

  private void updateUI(FirebaseUser user) {
    Log.d(TAG, "update screen");
    Intent nextActivity = new Intent(this, HostOrJoin.class);
    startActivity(nextActivity);
  }

  public void verifyLogin(final String email, final String password) {

    class VerifyLoginAsyncTask extends AsyncTask<Void, Void, String> {

      ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "", "", true);

      @Override
      protected void onPostExecute(String result) {
        super.onPostExecute(result);
        dialog.cancel();

        if (result != null) {
          try {
            JSONArray jsonarray = new JSONArray(result);
            for (int i = 0; i < jsonarray.length(); i++) {
              JSONObject jsonobject = jsonarray.getJSONObject(i);
              Log.d(TAG, result);
              //                        JSONObject items = new JSONObject(result);
              //                        int value = items.getInt("value");
              if (Integer.parseInt(jsonobject.getString("id")) == 1) {
                Toast.makeText(
                        LoginActivity.this,
                        "Please enter correct e-mail or password!",
                        Toast.LENGTH_LONG)
                    .show();
              } else {

                //                            JSONArray userDataArray =
                // items.getJSONArray("users_data");
                //                            JSONObject userDataJsonObject = userDataArray
                //                                    .getJSONObject(0);

                Log.d(TAG, "email " + jsonobject.getString("name"));
                Log.d(TAG, "password" + jsonobject.get("Hostname"));
                SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor ed = prefs.edit();
              }
            }

            EditText email = (EditText) findViewById(R.id.email_text);
            email.setText("");
            EditText password = (EditText) findViewById(R.id.password_text);
            password.setText("");
            updateUI();

          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      protected String doInBackground(Void... params) {
        StringBuilder responseStr = new StringBuilder();
        try {
          String url = ApplicationConstants.BASE_URL;
          //                            + email + "&password="
          //                            + Uri.encode(password);
          Log.d(TAG, url);
          HttpGet httpGet = new HttpGet(url);

          HttpResponse response = HttpConnectionManager.getClient().execute(httpGet);

          InputStream responseInputStream = response.getEntity().getContent();
          BufferedReader bufferedReader =
              new BufferedReader(new InputStreamReader(responseInputStream));

          String responseLineStr = null;
          while ((responseLineStr = bufferedReader.readLine()) != null) {
            responseStr.append(responseLineStr);
          }

          bufferedReader.close();
          responseInputStream.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        Log.d(TAG, responseStr.toString());
        return responseStr.toString();
      }
    }

    VerifyLoginAsyncTask verifyLoginAsyncTask = new VerifyLoginAsyncTask();
    verifyLoginAsyncTask.execute((Void) null);
  }

  public void forgotPasswordButton(View view) {
    Intent mainIntent = new Intent(LoginActivity.this, SwipeSearch.class);
    // mainIntent.putExtras(bundleData);
    startActivity(mainIntent);
  }

  public static String bundle2string(Bundle bundle) {
    if (bundle == null) {
      return null;
    }
    String string = "Bundle {";
    for (String key : bundle.keySet()) {
      string += " " + key + " => " + bundle.get(key) + ";";
    }
    string += " }";
    return string;
  }

  private void updateUI() {
    Intent nextActivity = new Intent(this, HostOrJoin.class);
    startActivity(nextActivity);
  }

  private void handleFacebookAccessToken(AccessToken token) {
    Log.d(TAG, "handleFacebookAccessToken:" + token);

    AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
    mAuth
        .signInWithCredential(credential)
        .addOnCompleteListener(
            this,
            new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                  // Sign in success, update UI with the signed-in user's information
                  Log.d(TAG, "signInWithCredential:success");
                  FirebaseUser user = mAuth.getCurrentUser();
                  updateUI(user);
                } else {
                  // If sign in fails, display a message to the user.
                  Log.w(TAG, "signInWithCredential:failure", task.getException());
                  Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT)
                      .show();
                  updateUI(null);
                }

                // ...
              }
            });
  }
}
