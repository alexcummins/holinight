package uk.com.holinight;

import org.json.JSONException;
import org.json.JSONObject;

public class fieldJSONParser {

  private String name;
  private String eventID;
  private String description;
  private String hostID;
  private String hostName;
  private String displayTime;
  private String startTime;
  private String endTime;
  private String location;
  private String maxRadius;
  private String dateCreated;
  private String dateModified;
  private String userName;
  private String userID;
  private String eventDescription;
  private String eventName;
  private String acceptDecline;
  private String eventImageURL;

  public String getEventImageURL() {
    return eventImageURL;
  }

  public void setEventImageURL(String eventImageURL) {
    this.eventImageURL = eventImageURL;
  }

  public String getUserImageURL() {
    return userImageURL;
  }

  public void setUserImageURL(String userImageURL) {
    this.userImageURL = userImageURL;
  }

  private String userImageURL;

  public fieldJSONParser() {}

  public fieldJSONParser(
      String name,
      String eventID,
      String description,
      String hostID,
      String hostName,
      String displayTime,
      String startTime,
      String endTime,
      String location,
      String maxRadius,
      String dateCreated,
      String dateModified,
      String userName,
      String userID,
      String acceptDecline,
      String eventName,
      String eventDescription,
      String eventImageURL,
      String userImageURL) {}

  public fieldJSONParser(JSONObject jsonObject) {

    try {
      this.name = jsonObject.getString("name");
      this.eventID = jsonObject.getString("eventID");
      this.description = jsonObject.getString("eventDescription");
      this.hostID = jsonObject.getString("hostID");
      this.hostName = jsonObject.getString("hostName");
      this.displayTime = jsonObject.getString("displayTime");
      this.startTime = jsonObject.getString("startTime");
      this.endTime = jsonObject.getString("endTime");
      this.location = jsonObject.getString("location");
      this.maxRadius = jsonObject.getString("maxRadius");
      this.dateCreated = jsonObject.getString("dateCreated");
      this.dateModified = jsonObject.getString("dateModified");
      //this.userName = jsonObject.getString("userName");
      this.userID = jsonObject.getString("userID");
      this.acceptDecline = jsonObject.getString("acceptDecline");
      this.eventImageURL = jsonObject.getString("eventImageURL");
      this.eventName = jsonObject.getString("eventName");
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  public String getEventDescription() {
    return eventDescription;
  }

  public void setEventDescription(String eventDescription) {
    this.eventDescription = eventDescription;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getAcceptDecline() {
    return acceptDecline;
  }

  public void setAcceptDecline(String acceptDecline) {
    this.acceptDecline = acceptDecline;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID = eventID;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHostID() {
    return hostID;
  }

  public void setHostID(String hostID) {
    this.hostID = hostID;
  }

  public String getHostname() {
    return hostName;
  }

  public void setHostname(String hostname) {
    this.hostName = hostname;
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

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }
}
