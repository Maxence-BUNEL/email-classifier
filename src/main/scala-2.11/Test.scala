import org.apache.spark._
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}


object Test {
  def main(args: Array[String]): Unit = {
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("Json Parser")
      .master("local[*]")
      .getOrCreate()

    val mails = spark.read.json("mails/-mail_zied.json")
      .select("_source.*")
      .select("mailboxId", "from", "to", "cc", "bcc")

    val mailboxes = spark.read.csv("mails/usersmailfoldersid.csv").toDF("mailboxId", "mailboxName")

    val mailsWithMailboxNames = mails.join(mailboxes, Seq("mailboxId"))
//test comment
    mails.printSchema()
    mailboxes.printSchema()
    mailsWithMailboxNames.printSchema()

    mailsWithMailboxNames.limit(1).foreach(printEntry(_))
  }

  def printEntry(row: Row) : Unit = {
    val users = row.getAs[Seq[String]]("from")
    val mailId = ""
    val mailbox = row.getAs[String]("mailboxName")
    println(s"mail with users $users for mail $mailId in $mailbox")
  }
}
