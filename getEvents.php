<?php
require_once __DIR__ . '/DbConnect.php';

$con = new DbConnect();
if (!$con)
{
    die('Could not connect: ' . mysql_error());
}else{
    error_reporting(0);
    date_default_timezone_set('Europe/London');
    $currentDayAndTime = date('Y-m-d H:i:s');
    $currentDay = date('Y-m-d');

    $queryString = "SELECT ue.userID, ue.eventID, ue.acceptDecline, e.eventName, e.hostID, e.eventDescription, e.location, e.maxRadius, e.eventImageURL, e.dateCreated, e.dateModified, e.displayTime, e.startTime, u.userImageURL, u.name FROM userEvents ue LEFT  JOIN event e on e.eventID = ue.eventID LEFT  JOIN  users u on u.userID = ue.userID":
	    $q=mysql_query($qString);

      while($record=mysql_fetch_assoc($q))
      {
          $no = 0;



          $output[]= $record;

      }

			$no = 1;
            echo '{"value":'.json_encode($no).',"events":'.json_encode($output).'}';


  }

$con = null;
?>