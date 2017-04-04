//Maps

val a = 1 to 5
val b = 'a' to 'g'
val c = a.zip(b) //will zip values of above two instance in a pair
val d = c.toMap //will create key value pair
val e = d(2) //looking up value(b) using it's key index (2)
//val f = d(9) //there is no key (9) so result will be "key not found"

//another way to find key/value

val g = d.get(2)
val h = d.getOrElse(1, "z") //if no key 1, return default z
val i = d.getOrElse(9, "z") //no key 9 so it returns default(z)

//higher order function
//map

val j = 1 to 5
val k = j.map(num => num * 2) //num here is just a temp name place holder
val l = j.map(_*2) //another way to do the above one. here _ is a placeholder

//flatMap
val m = List("Scala", "Python", "R") //list of string = squence of character. "Scala" = (s,c,a,l,a)
val n = m.map(lang => lang + '#') //map # to the list of strings
val o = m.flatMap(lang => lang) //lang is just a text place holder
val p = {m.flatMap(lang => lang)}.map(hash => hash + "#") //adding # at the end of every character

//filter

val q = List("Scala", "SQL", "Python", "RS", "SoftwaRe")
val r = q.filter(word => word.contains("S")) //it will filter and return only string that contains "s"
val s = q.filter(name => name.contains("R") || name.contains("P"))

//foreach

val t = List(1,2,3,4,5)
val u = t.map(println) //it will print the values but also the collection of lists -> waste of jvm memory
//instead use foreach function
val v = t.foreach(println)

//forall
//it will return boolean value

val x = List("Scala", "SQL", "Stellar", "Software")
val y = x.forall(word => word.contains("S")) //checks to see if all strings in list has value "S"
val z = x.forall(_.contains("a")) //returns false because not all strings has "a"












