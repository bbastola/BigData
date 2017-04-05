/**
  * Created by bbastola on 3/14/17.
  */
import com.typesafe.config.ConfigFactory
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object wc {

  def main(args: Array[String]): Unit = {
    
    val props = ConfigFactory.load()
    val conf = new SparkConf().
      setMaster(props.getConfig(args(2)).getString("executionMode")).
      setAppName("Word Count")

    val sc = new SparkContext(conf)
    val randomtext = sc.textFile(args(0))
    randomtext.flatMap(rec => rec.split(" ")).
      map(rec => (rec, 1)).
      reduceByKey((agg, value) => agg + value).
      saveAsTextFile(args(1))
  }
}
