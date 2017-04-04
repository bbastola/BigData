val number = List(1,2,3,4,5)
number.::(0)
(5)::number
number:+ 55

number.head
number.tail
number.sum
number.take(3)
number.contains(8)
val num = List(6,7,8,9,10)
val z = num.zip(number)

val Num = List(1,2,3,4,5,6,7,8,9,10)
Num.head
Num.last
Num.size
Num.take(6)
Num.take(9)
Num.takeRight(3)
Num.takeRight(9)
Num.contains(7)
Num.indexOf(4)
Num.::(0)
(0)+:Num
val N = List(10,11,12,13,14,15)
N.zip(Num)


//private vs public

class Hello {
  private val message: String = "Hello"
}

val H = new Hello
//H.message //not possible

class Hello1 {
  val message: String = "Hello"
}

val H1 = new Hello1
H1.message













