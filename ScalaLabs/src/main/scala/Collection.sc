val z = 1 to 10

z.map(n => n + 1)

//Array
val a: Array[Int] = Array(1,2,3,4,4,5,5)
a(2)
a:+(1)
a.distinct
a

//set
val s = Set(1,2,3,4,4,4,5,5,6,6,7) //will not hold duplicate. no order guarented
s.size
s + 11 + 12
s(3) //unlike list, array set will check and see if the value exist instead of
     //returning the value
s(14)

val t = s.toSeq
val x = t.toSet

//option
val B = Option(null)
B.getOrElse("Foo")

case class Customer (
                     first: String = "",
                     middle: Option[String] = None,
                     last: String = "")
Customer("Jane", Option("J"), "Jones")


//tuple

val tuple = (1, "a", 2, "b")

(tuple._4)

val (first, second, third, fourth) = tuple

val c = (1, "a") //tuple2 are pairs
val q = (1 -> "a") //same as above
val g = (1 -> "a" -> 4)


















