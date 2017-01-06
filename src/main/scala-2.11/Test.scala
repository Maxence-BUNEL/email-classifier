import org.apache.spark._

object Test {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[*]", "test")
    val data = sc.parallelize(Seq("a" , "b" , "c"))
    val withIndex = data.zipWithIndex();
    withIndex.foreach(println(_))
  }
}
