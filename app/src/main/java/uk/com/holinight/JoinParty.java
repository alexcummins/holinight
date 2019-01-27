package uk.com.holinight;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.io.InputStream;
import java.net.URL;

public class JoinParty extends AppCompatActivity {
private final String TAG = "JoinParty";
private  ImageView partyImage;
    String facebookUserId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_party);
        partyImage = (ImageView) findViewById(R.id.partyImage);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



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
    }
}
