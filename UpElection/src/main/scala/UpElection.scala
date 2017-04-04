/**
  * Created by bbastola on 3/25/17.
  */

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object UpElection {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Up Election").setMaster("local")
    val sc = new SparkContext(conf)


    val electionData = sc.textFile("/Users/bbastola/Documents/Github/Dataset/electionresults").
      mapPartitionsWithIndex((idx, iter) => if(idx == 0) iter.drop(1) else iter). //drops the header
      filter(rec => rec.contains("Uttar Pradesh")) //filter records and fetch only record from Utter Pradesh

    val electionDataUp = electionData.map(rec => {
      val r = rec.split("\t")
      ((r(0), r(1)), ((r(6), r(10).toInt))) //fetching only needed columns
    }).groupByKey() //grouping the data into (key, value) pair for transformation

    //defining a new scala function to transform the above data
    def recalculateWithAlly(rec: Iterable[(String, Int)]): Iterable[(String, Int)] = {
      rec.map(r => {
        if (r._1 == "SP" || r._1 == "INC")
          ("Ally", r._2)
        else
          r
      }).groupBy(r => r._1).  //grouping By key(party) i.e. ally -> list{(ally, votes), (ally, votes)}
        map(r => (r._1, r._2.map(r => r._2 + r._2).sum)) //mapping values and adding ally votes
    }
      val finalResult = electionDataUp.map(rec => (rec._1, recalculateWithAlly(rec._2))).
        map(rec => (rec._1, rec._2.toList.sortBy(k => -k._2))).map(rec => ((rec._1._1 , rec._2(0)._1), 1)).
        countByKey()
    finalResult.foreach(println)
  }
}
