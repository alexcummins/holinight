 
<?php
    require_once __DIR__ . '/dbConnect.php';
	$eventName = $_REQUEST['eventName'];
	$location = $_REQUEST['location'];
	$eventDescription = $_REQUEST['eventDescription'];
	$maxRadius = $_REQUEST['maxRadius'];
	$startTime = $_REQUEST['startTime'];
echo $eventName;
$hostID = $_REQUEST['userID'];
    $con = new DbConnect();
	if (!$con)
	{
		die('Could not connect: ' . mysql_error());
	}else{
mysql_query("INSERT INTO event (eventName, location, eventDescription, maxRadius, hostID) VALUES ('".$eventName."', '".$location."', '".$eventDescription."', '". $maxRadius. "', '".$hostID."') ");
	}
echo('1');
$con = null;

?> 