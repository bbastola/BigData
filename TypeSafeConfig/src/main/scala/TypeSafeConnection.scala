/**
  * Created by bbastola on 3/13/17.
  */
import java.sql.DriverManager

import com.typesafe.config._

//--STEP 3--defining case class, overriding toString() function and definining a finction to calculate commission amount
case class EmployeeDetails(first_name: String,
                          last_name: String,
                          salary: Double,
                          commission_pct: Double) {
  override def toString: String = {
    "First Name: " + first_name + " | " + "Last Name: " + last_name + " | " + "Salary: " + salary + " | " + "Commission: " + Commission
  }
  //defining a function to calculate commission amount
  def Commission = {
    if(commission_pct == 0) "No Commission"
    else(salary * commission_pct)
  }
}

//--STEP 1 --define object that holds connection info
object TypeSafeConnection {
  def main(args: Array[String]): Unit = {
    val props = ConfigFactory.load() //loads application properties file from resources
    val driver = "com.mysql.jdbc.Driver" //registering mysql driver

    //the arguments listed on application properties file will be pulled and declared below
    val host = props.getConfig(args(0)).getString("host")
    val port = props.getConfig(args(0)).getString("port")
    val db = props.getConfig(args(0)).getString("db")
    val url = "jdbc:mysql://" + host + ":" + port + "/" + db
    val userName = props.getConfig(args(0)).getString("userName")
    val password = props.getConfig(args(0)).getString("password")

    //creating connection statement and establishing connection
    Class.forName(driver)
    val connection = DriverManager.getConnection(url, userName, password)
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT first_name, last_name, salary, commission_pct FROM employees")
    //the resultSet will hold results form the query in the form of collection

    //--STEP 2-- next we have to convert the resultSet(java) api collection to scala api and apply functional logic to print the records
    //first Iterator.continually is taking an arguments as tuple of (resultset and resultSet function (checks true or false) and returns tuple(record, true)
    //takeWhile will check the second element of tuple for true.
    //And if true .map will fetch the first element of tuple.

    val A = Iterator.continually(resultSet, resultSet.next()).takeWhile(placeholder => placeholder._2).map(placeholder => placeholder._1)
    val B = A.map(placeholder => { //map function here will assign each record into case class
      EmployeeDetails(//EmployeeDetails here is a case object derived from case class above so new keyword(to make a new object) is not needed
        placeholder.getString("first_name"),
        placeholder.getString("last_name"),
        placeholder.getDouble("salary"),
        placeholder.getDouble("commission_pct")
      )
    })
      val C = B.foreach(placeholder => println(placeholder))
    }
}
