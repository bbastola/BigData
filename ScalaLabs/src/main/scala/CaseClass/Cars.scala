package CaseClass

/**
  * Created by bbastola on 3/9/17.
  */
object BMW {
  def main(args: Array[String]): Unit = {
    val car = Car("bmw", "blue")
    println(car.brand)
    val car1 = Car("bmw", "blue")
    val car2 = Car("bmw", "blue")
    if(car1 == car2) println("True") else println("false")
    car2.color = "Red"
    println(car2.color)
    val car3 = car2.copy(color = "black")
    println(car3)
  }
}
