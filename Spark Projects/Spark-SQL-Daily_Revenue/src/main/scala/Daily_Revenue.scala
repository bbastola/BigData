/**
  * Created by bbastola on 5/15/17.
  */
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

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

object Daily_Revenue {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Spark Daily Revenue").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    sqlContext.setConf("spark.sql.shuffle.partitions", "2")

    import sqlContext.implicits._
    import org.apache.spark.sql.functions._

    val orders = sc.textFile("/Users/bbastola/Documents/Github/Dataset/retail_db/orders/part-00000")
    val orderItems = sc.textFile("/Users/bbastola/Documents/Github/Dataset/retail_db/order_items/part-00000")

    val ordersDF = orders.map(
      rec => {
        val r = rec.split(",")
        Orders(r(0).toInt, r(1), r(2).toInt, r(3))
      }
    ).toDF

    val orderItemsDF = orderItems.map(
      rec => {
        val r = rec.split(",")
        OrderItems(r(0).toInt, r(1).toInt, r(2).toInt, r(3).toInt, r(4).toDouble, r(5).toDouble)
      }
    ).toDF

    val ordersFiltered = ordersDF.filter(ordersDF("order_status") === "COMPLETE" or ordersDF("order_status") === "CLOSED")
    val joinedData = ordersFiltered.join(orderItemsDF, ordersFiltered("order_id") === orderItemsDF("order_item_order_id"))
    val dailyRevenue = joinedData.groupBy("order_date").
      agg(sum("order_item_subtotal")).sort("order_date").rdd.saveAsTextFile("/Users/bbastola/Documents/Output/")
  }
}
