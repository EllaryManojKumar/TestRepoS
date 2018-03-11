package kafka.spark

class KafkaSparkConsumar {

  def main(args: Array[String]): Unit = {
    // Define the basic information otherwise take it from arguments
    var (batchTime, topics, brokers, isJson) = (2, "topic1,topic2", "localhost:9092", false)

    if (args.length == 4) {
      batchTime = args(0).toInt
      topics = args(1)
      brokers = args(2)
      isJson = args(3).toBoolean
    }
    val conf = new SparkConf().setMaster("local[1]").setAppName("KafkaSparkConsumar")
    val ssc = new StreamingContext(conf, Seconds(1))

    val topicsSet = topics.split(",").toSet

    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)

    val data = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet).map(_._2)

    val parsedDstream = data.map(parseMessagge(_).filter(_ != None).map(_.get)

    /////parsedDstream.foreachRDD(
     // rdd => rdd.saveToPhoenix("productlog", Seq("userid", "username", "email", "product", "transaction", "country", "state", "city", "dt"), zkUrl = Some("localhost:2181")))
    parsedDstream.foreachRDD(
      rdd =>rdd.write.saveAsTable(tableName))
    // parsedDstream.print()

    ssc.start()

    ssc.awaitTermination()
  }

  def parseMessagge(eachRow: String): Option[ProductLog] = {
    Try {
      val obj = new Gson().fromJson(eachRow, classOf[JsonObject])
      if (obj.entrySet().size() == 9) {
        Option(ProductLog(obj.get("userid").getAsLong(), obj.get("username").getAsString(), obj.get("email").getAsString(), obj.get("product").getAsString(), obj.get("transaction").getAsString(), obj.get("country").getAsString(), obj.get("state").getAsString(), obj.get("city").getAsString(), obj.get("dt").getAsString()))
      } else {
        None
      }
    }.getOrElse(None)
  }

  // ProductLog details:  userid,username,email,product,transaction,country,state,city,dt
  case class ProductLog(userid: Long, username: String, email: String, product: String, transaction: String, country: String, state: String, city: String, dt: String) {
    override def toString() = { userid + "," + username + "," + email + "," + product + "," + transaction + "," + country + "," + state + "," + city + "," + dt }
  }
}
