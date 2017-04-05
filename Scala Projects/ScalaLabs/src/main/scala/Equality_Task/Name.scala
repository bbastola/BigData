package Equality_Task

/**
  * Created by bbastola on 3/9/17.
  */
object Person {
  def main(args: Array[String]): Unit = {
    val M = new Person("Martin", "Johns")
    val N = new Person("Martin", "Johns")
   if(M == N) print("true") else print("false") //because we have used case class, the output will be true
  }
}

