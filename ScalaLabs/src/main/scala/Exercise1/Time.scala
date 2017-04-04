package Exercise1

/**
  * Created by bbastola on 3/9/17.
  */
class Time(val Hours: Int, val Minutes: Int, val Seconds: Int) {

  require(Hours >=1 && Hours <=12, message = "Invalid number")
  require(Minutes >=0 && Minutes <=60, message = "Invalid number")
  require(Seconds >=0 && Seconds <=60, message = "Invalid number")

  def minutesOfDay: Int = Minutes

  println(Hours + " " + Minutes + " " + Seconds)

  def apply(Hours: Int, Minutes: Int, Seconds: Int) = {
    println("In apply Method")
  }
}

object Time {
  def apply(Hours: Int, Minutes: Int, Seconds: Int): Unit = {
    println(Hours + " " + Minutes)
  }
}
