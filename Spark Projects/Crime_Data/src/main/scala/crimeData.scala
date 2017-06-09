/**
  * Created by bbastola on 5/31/17.
  */
import org.apache.spark.{SparkConf, SparkContext}

object crimeData {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Crime Data analytics").setMaster("local")
    val sc = new SparkContext(conf)

    //Find number of crimes that happened under each FBI code using core Spark.

    val data = sc.textFile("/Users/bbastola/Downloads/Crime_dataset")

    val fbiCode = data.map(rec => rec.split(",")(14)).
      map(rec => (rec, 1)).
      reduceByKey((agg, value) => agg + value).
      saveAsTextFile("/Users/bbastola/Desktop/Output")

    // Find number of ‘NARCOTICS’ cases filed in the year 2015

    val narcoticsFilled = data.filter(rec => rec.contains("NARCOTICS")).count()
      //once you perform action above (count) the only way to save the result is by converting the value to RDD
    val narcoticsCount = sc.parallelize(Seq(narcoticsFilled)).
      saveAsTextFile("/Users/bbastola/Desktop/Output1")

    //Find the number of theft related arrests that happened in each district

    val theftCrime = data.filter(rec => rec.contains("THEFT")).
                      map(rec => rec.split(",")(11)).
                      filter(rec => !(rec.contains("false"))).
                      map(rec => (rec, 1)).
                      reduceByKey((agg, value) => agg + value).saveAsTextFile("/Users/bbastola/Desktop/Output2")
  }
}
