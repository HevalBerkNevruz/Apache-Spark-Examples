import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by heval on 11/3/15.
 */
public class WordCountExample implements Serializable {
    public void wordCount(String path, String target, SparkConf conf) {
        JavaSparkContext spark = new JavaSparkContext(conf);
        JavaRDD<String> textFile = spark.textFile(path);
        JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {
            public Iterable<String> call(String s) {
                return Arrays.asList(s.split(" "));
            }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer a, Integer b) {
                return a + b;
            }
        });
        counts.saveAsTextFile(target);
    }
}
