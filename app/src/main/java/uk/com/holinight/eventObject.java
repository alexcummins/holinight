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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description = "";
    private String hostName = "";
    private String eventDescription = "";
    private String location = "";
    private String maxRadius = "";
    private String eventImageURL = "";
    private String dateCreated = "";
    private String dateModified = "";
    private String displayTime = "";
    private String startTime = "";
    private String userImageURL = "";

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAcceptDecline() {
        return acceptDecline;
    }

    public void setAcceptDecline(String acceptDecline) {
        this.acceptDecline = acceptDecline;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getHostID() {
        return hostID;
    }

    public void setHostID(String hostID) {
        this.hostID = hostID;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(String maxRadius) {
        this.maxRadius = maxRadius;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }

    public void setEventImageURL(String eventImageURL) {
        this.eventImageURL = eventImageURL;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name = "";
    private static final String TAG = "Event Object";

    eventObject(JSONObject eventJSONObject){

        fieldJSONParser eventJSON  = new fieldJSONParser(eventJSONObject);
        this.userID = eventJSON.getUserID();
        this.eventID = eventJSON.getEventID();
        this.acceptDecline = eventJSON.getAcceptDecline();
        this.eventName = eventJSON.getEventName();
        this.hostID = eventJSON.getHostID();
        this.description = eventJSON.getDescription();
        this.hostName = eventJSON.getHostname();
        this.location = eventJSON.getLocation();
        this.maxRadius = eventJSON.getMaxRadius();
        this.eventImageURL = eventJSON.getEventImageURL();
        this.dateCreated = eventJSON.getDateCreated();
        this.dateModified = eventJSON.getDateModified();
        this.displayTime = eventJSON.getDisplayTime();
        this.startTime = eventJSON.getStartTime();
        this.userImageURL = eventJSON.getUserImageURL();
        this.eventDescription = eventName + "\n\n" + hostName + "\n\n" + startTime  + "\n\n" + description;
        Log.d(TAG, eventID);

    }

}
