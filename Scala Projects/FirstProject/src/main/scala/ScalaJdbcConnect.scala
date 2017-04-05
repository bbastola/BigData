/**
  * Created by bbastola on 3/12/17.
  */

import java.sql.DriverManager
import java.sql.Connection

    //creating case class which will be needed during iteration of resultSet below --STEP 3
case class EmployeeDetails(first_name: String,
                           last_name: String,
                           salary: Double,
                           commission_pct: Double) {
      //overriding toString function --STEP 6
      override def toString: String = {
        "First Name: " + first_name + " | " + "Last Name: " + last_name + " | " + "Salary: " + salary + " | " + "Commission: " + empCommission
      }

      //defining a function to calculate salary commission --STEP 5
      def empCommission = {
        if (commission_pct == 0) "No commission"
        else (salary * commission_pct)
      }

    }

    //creating connection object --STEP 1
object ScalaJdbcConnect {
  def main(args: Array[String]): Unit = {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://nn01.itversity.com:3306/hr"
    val userName = "hr_ro"
    val password = "itversity"

    //registering the driver
    Class.forName(driver)
    //making the connection
    val connection = DriverManager.getConnection(url, userName, password)

    //creating statement and running Query --STEP 2
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT first_name, last_name, salary, commission_pct FROM employees")

    //running iteration through the query result to extract and print employee records --STEP 4
    while(resultSet.next()) {
      val E = EmployeeDetails(
        resultSet.getString("first_name"),
        resultSet.getString("last_name"),
        resultSet.getDouble("salary"),
        resultSet.getDouble("commission_pct")
      )
      println(E)
    }

  }

}
