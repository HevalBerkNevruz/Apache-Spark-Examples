import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.rdd.RDD;

/**
 * Created by heval on 11/3/15.
 */
public class NaiveBayesExample {

    public double training(String documentPath, SparkConf conf) {
        JavaSparkContext context = new JavaSparkContext(conf);
        RDD trainingData = MLUtils.loadLabeledData(context.sc(), documentPath);
        final NaiveBayesModel trained = NaiveBayes.train(trainingData);
        Vector vector = Vectors.dense(new double[]{3, 4, 1, 0, 0, 0});
        return trained.predict(vector);
    }
}
