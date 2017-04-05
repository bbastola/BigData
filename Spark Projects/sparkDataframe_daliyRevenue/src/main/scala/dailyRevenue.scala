/**
  * Created by bbastola on 4/5/17.
  */
//import necessary packages
import com.typesafe.config.ConfigFactory
import org.apache.hadoop.fs._
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

//define case classes
case class Orders(
                   order_id: Int,
                   order_date: String,
                   order_customer_id: Int,
                   order_status: String)

case class OrderItems(
                       order_item_id: Int,
                       order_item_order_id: Int,
                       order_item_product_id: Int,
                       order_item_quantity: Int,
                       order_item_subtotal: Double,
                       order_item_product_price: Double)
object dailyRevenue {
  def main(args: Array[String]): Unit = {

    //create spark context and set application name/running mode
    val appConf = ConfigFactory.load()
    val conf = new SparkConf().setAppName("Daily Revenue - Spark SQL").
      setMaster(appConf.getConfig(args(2)).getString("deploymentMaster"))
    val sc = new SparkContext(conf)

    //create sql context
    val sqlContext = new SQLContext(sc)
    sqlContext.setConf("spark.sql.shuffle.partitions", "2")

    import sqlContext.implicits._

    //now we have to create Data frames using map functions and case classes
    val inputPath = args(0)
    val outputPath = args(1)

    //checking input and output path
    val fs = FileSystem.get(sc.hadoopConfiguration)
    val inputPathExists = fs.exists(new Path(inputPath))
    val outputPathExists = fs.exists(new Path(outputPath))

    if(!inputPathExists) {
      println("Input Path does not exists")
      return
    }

    if(outputPathExists) {
      fs.delete(new Path(outputPath), true)
    }

    val ordersDF = sc.textFile(inputPath + "/orders").
      map(rec => {
        val r = rec.split(",")
        Orders(r(0).toInt, r(1).toString, r(2).toInt, r(3).toString)
      }).toDF()

    val orderItemsDF = sc.textFile(inputPath + "/order_items").
      map(rec => {
        val r = rec.split(",")
        OrderItems(r(0).toInt, r(1).toInt, r(2).toInt, r(3).toInt, r(4).toDouble, r(5).toDouble)
      }).toDF

    //we have to filter the orders table for complete/closed records

    val ordersDFFiltered = ordersDF.filter(ordersDF("order_status") === "COMPLETE" or ordersDF("order_status") === "CLOSED")

    //now we join two tables

    val ordersJoin = ordersDFFiltered.join(orderItemsDF, ordersDFFiltered("order_id") === orderItemsDF("order_item_order_id"))

    //finally we calculate the daily revenue

    val dailyRevenue = ordersJoin.groupBy("order_date").
      agg(sum("order_item_subtotal")).
      rdd.saveAsTextFile(outputPath)
  }
}
