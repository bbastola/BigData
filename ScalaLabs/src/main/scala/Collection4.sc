//groupBy
 val a = 1 to 5
val b = a.groupBy(num => num % 2)
//the result for groupBy will be in map function form ( key, value)
//Map(1 -> Vector(1,3,5), 0 -> Vector(2, 4))
//Map(1 is key, Vector(1,3,5) it's values), (0 is key, Vector(2,4) it's values)

//takeWhile and dropWhile
val c = a.takeWhile(num => num < 3) //takes only number less than 3 and prints them
val d = a.takeWhile(num => num < 2) //it should always be less than and not >
val e = a.dropWhile(num => num < 4) //drops everything less than 4 and prints 4 or above

//lazy val

val x = {println("x"); 2}
lazy val y = {println("y"); 4}



