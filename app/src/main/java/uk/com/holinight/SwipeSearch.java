package uk.com.holinight;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;


public class SwipeSearch extends AppCompatActivity {

    private final String TAG = "Swipe Search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnTouchListener(new OnSwipeTouchListener(SwipeSearch.this) {
            public void onSwipeRight() {
                Log.d(TAG, "Swipe Right");
            }
            public void onSwipeLeft() {
                Log.d(TAG, "Swipe Left");
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
