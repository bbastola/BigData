/**
  * Created by bbastola on 3/17/17.
  */
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import com.typesafe.config._

object dailyRevenue {
  def main(args: Array[String]): Unit = {

    val props = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Daily Revenue").
      setMaster(props.getConfig(args(2)).getString("deploymentMaster"))

    val sc = new SparkContext(conf)

    val baseInputDir = args(0)
    val baseOutputDir = args(1)

    val fs = FileSystem.get(sc.hadoopConfiguration)
    val inputPathExists = fs.exists(new Path(baseInputDir))
    val outputPathExists = fs.exists(new Path(baseOutputDir))

    if (!inputPathExists) {
      println("Input Path does not exists")
      return
    }

    if(outputPathExists){
      fs.delete(new Path(baseOutputDir), true)
    }

    val orders = sc.textFile(baseInputDir + "/orders").
      filter(rec => rec.contains("CLOSED") || rec.contains("COMPLETE")).
      map(rec => (rec.split(",")(0).toInt, rec.split(",")(1)))

    val orderItems = sc.textFile(baseInputDir + "/order_items").
      map(rec => (rec.split(",")(1).toInt, rec.split(",")(4).toDouble))

    val dailyRevenue = orders.join(orderItems).
      map(rec => rec._2).
      groupByKey().
      map(rec => (rec._1, rec._2.sum)).
      sortByKey().
      saveAsTextFile(baseOutputDir)
  }

}