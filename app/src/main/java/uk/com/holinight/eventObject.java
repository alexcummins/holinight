package uk.com.holinight;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class eventObject {
    private String userID = "";
    private String eventID = "";
    private String acceptDecline = "";
    private String eventName = "";
    private String hostID = "";
    private String eventDescription = "";
    private String location = "";
    private String maxRadius = "";
    private String eventImageURL = "";
    private String dateCreated = "";
    private String dateModified = "";
    private String displayTime = "";
    private String startTime = "";
    private String userImageURL = "";
    private String name = "";
    private static final String TAG = "Event Object";

    eventObject(JSONObject eventJSONObject){

        fieldJSONParser eventJSON  = new fieldJSONParser(eventJSONObject);
        this.userID = eventJSON.getUserID();
        this.eventID = eventJSON.getEventID();
        this.acceptDecline = eventJSON.getAcceptDecline();
        this.eventName = eventJSON.getEventName();
        this.hostID = eventJSON.getHostID();
        this.eventDescription = eventJSON.getEventDescription();
        this.location = eventJSON.getLocation();
        this.maxRadius = eventJSON.getMaxRadius();
        this.eventImageURL = eventJSON.getEventImageURL();
        this.dateCreated = eventJSON.getDateCreated();
        this.dateModified = eventJSON.getDateModified();
        this.displayTime = eventJSON.getDisplayTime();
        this.startTime = eventJSON.getStartTime();
        this.userImageURL = eventJSON.getUserImageURL();
        Log.d(TAG, eventID);

    }

}
