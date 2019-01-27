<?php
/**
 * Created by PhpStorm.
 * User: alex
 * Date: 27/01/19
 * Time: 06:32
 */
include_once './config.php';

class dbConnect
{

    private $conn;

    // constructor
    function __construct()
    {

        // connecting to database
        $this->connect();
    }

    // destructor
    function __destruct()
    {
        // closing db connection
        $this->close();
    }

    /**
     * Establishing database connection
     * @return database handler
     */
    function connect()
    {

        // Connecting to mysql database
        $this->conn = mysql_connect(HOST, USERNAME, PASSWORD) or die(mysql_error());
        //$this->conn = mysqli_connect(HOST, USERNAME, PASSWORD, DATABASE_NAME) or die(mysql_error());

        mysql_set_charset("UTF8", $this->conn);

        // Selecting database
        mysql_select_db(DATABASE_NAME) or die(mysql_error());

        // returing connection resource
        return $this->conn;


    }

    /**
     * Closing database connection
     */
    function close()
    {
        // closing db connection
        mysql_close($this->conn);
    }
}

?>