/**
  * Created by bbastola on 3/29/17.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object topNProducts {
  def main(args: Array[String]): Unit = {
   val conf = new SparkConf().setAppName("Top N Products").setMaster("local")
   val sc = new SparkContext(conf)

   val products = sc.textFile("/Users/bbastola/Documents/Github/Dataset/retail_db/products").
     filter(rec => rec.split(",")(4) != "").map(rec => {
     val r = rec.split(",")
     ((1).toInt, rec)
   })
   val categories = sc.textFile("/Users/bbastola/Documents/Github/Dataset/retail_db/categories").
     map(rec => (rec.split(",")(0).toInt, rec.split(",")(2)))

    val prodCat = products.join(categories).map(rec =>
      (rec._2._2, rec._2._1)).groupByKey()

    def topNProducts(rec: (String, Iterable[String]), topN: Int): Iterable[(String, String)] = {
      rec._2.toList.sortBy(k => -k.split(",")(4).toFloat).take(topN).map(r => (rec._1, r))
    }

    val finalResult = prodCat.flatMap(rec => topNProducts(rec, 9)).
      saveAsTextFile("/Users/bbastola/Documents/Github/Output")
  }
}
