//reduce
val a = 1 to 5
val b = a.reduce((occ, cur) => occ + cur) // (temp values)occ = occurance cur = current value
//for every occurance, add it with current value
val c = a.reduce(_ + _)
//another way (meaning less)
 //however this reduce function has a drawback when it comes to empty list. see below
val d = List()
//val e = d.reduce((occ, cur) => occ + cur) //it won't work because the list is empty
//val f = List[Int]().reduce((occ, cur) => occ + cur) //same as above
//to overcome this we use following function

//fold, foldLeft, foldRight

val g = 1 to 5
val h = g.foldLeft(0){case (occ, cur) => occ + cur} //0 is the initial value
val i = g.foldLeft(5){case (occ, cur) => occ + cur} //5 is the initial value
val j = List[Int]().foldLeft(0) {case (occ, cur) => occ + cur}
//no error above despote list being 0 unlike reduce function where it throws error
val k = List[Int]().foldRight(5) {case (occ, cur) => occ + cur} //prints default value 5

//product
val l = 1 to 5
val m = l.product //multiplies numbers in list

//exists - it will return boolean value
val n = l.exists(num => num == 3) //check if the num 3 exists in the list. uses iteration =>
val o = l.exists(num => num == 6) //there is no number 6 in the list so it will return false

//find
val p = l.find(num => num == 3) //it will find the value and return it
val q = l.find(num => num == 8) //it will return none because find is of option type















