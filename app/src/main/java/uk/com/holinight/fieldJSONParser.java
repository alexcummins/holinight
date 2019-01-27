package uk.com.holinight;

import org.json.JSONException;
import org.json.JSONObject;

public class fieldJSONParser {

  private String name;
  private String eventID;
  private String hostID;
  private String hostname;
  private String displayTime;
  private String startTime;
  private String endTime;
  private String location;
  private String maxRadius;
  private String dateCreated;
  private String dateModified;

  public fieldJSONParser() {}

  public fieldJSONParser(
      String name,
      String eventID,
      String hostID,
      String hostname,
      String displayTime,
      String startTime,
      String endTime,
      String location,
      String maxRadius,
      String dateCreated,
      String dateModified) {}

  public fieldJSONParser(JSONObject jsonObject) {

    try {
      this.name = jsonObject.getString("name");
      this.eventID = jsonObject.getString("eventID");
      this.hostID = jsonObject.getString("hostname");
      this.hostname = jsonObject.getString("hostname");
      this.displayTime = jsonObject.getString("displayTime");
      this.startTime = jsonObject.getString("statTime");
      this.endTime = jsonObject.getString("endTime");
      this.location = jsonObject.getString("location");
      this.maxRadius = jsonObject.getString("maxRadius");
      this.dateCreated = jsonObject.getString("dateCreated");
      this.dateModified = jsonObject.getString("dateModified");

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
