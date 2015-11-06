import org.apache.spark.SparkConf;

public class Main {

    private static final String HDFS = "hdfs://localhost:9000/";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("Apache Spark Example").setMaster("local");
        new WordCountExample().wordCount(HDFS + "data.txt", HDFS + "result", conf);
        System.out.println(new NaiveBayesExample().training(HDFS + "data.txt", conf));
    }
}
