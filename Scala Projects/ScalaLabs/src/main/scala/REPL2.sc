import com.sun.org.apache.xerces.internal.impl.dv.xs.AnyURIDV

//companion object example

object Hello {
  private val defmessage: String = "Hello There"
}

class Hello(message: String = Hello.defmessage) {
  println(message)
}

val Test = new Hello()

//apply method

object Reverse{
  def apply(mes: String): String =
    mes.reverse
}

Reverse("New York")

//copy()
case class Time(hours: Int = 0, minutes: Int = 0)
@volatile var T = new Time(9, 45) //use @volatile for thread safety
T = T.copy(minutes = 122)

//syntactic sugar. each collection has companion object will apply method
class Animal
class Dog extends Animal
class Cat extends Animal

val A = List(new Cat, new Animal)
