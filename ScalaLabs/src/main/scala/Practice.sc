//adding only even numbers from the list of numbers between 1 to 100
val a = (1 to 100).toList
val b = a.filter(num => num % 2 == 0).sum

//adding just the numbers from collection of tuples
val c = List((1, "Hi"), (2, "You"), (3, "Doing"))
val d = c.map(num => num._1).sum
val e = c.map(num => num._2)
val f = c.map(num => num._2).mkString(" ") //making one string from list of string

//partial function
def adder(m:Int, n: Int, p: Int) = m + n + p
val add = adder(2, _:Int, _:Int)
add(3,5)

//partition
//Splits as list based on the function

val g = List(1,2,3,4,5,6,7,8,9,10)
val h = g.partition(num => num % 2 == 0)

//flatten and filtering string from list

val i = List(List(1,2), List(3,4), List("get", "on", "the", "Dance", "floor"))
val j = i.flatten
val k = j.filter {
  case s: String => true
  case _ => false
}.mkString(" ")

