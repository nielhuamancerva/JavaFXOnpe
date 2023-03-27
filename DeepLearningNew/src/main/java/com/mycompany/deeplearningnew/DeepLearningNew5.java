package com.mycompany.deeplearningnew;
import com.twelvemonkeys.image.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.datavec.image.loader.Java2DNativeImageLoader;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;
//import org.nd4j.linalg.util.ImageUtil;
/**
 *
 * @author JRoque
 */
public class DeepLearningNew5 {
    public static void main(String[] args) throws IOException {
        int inputSize = 784; // 28 x 28
        int outputSize = 10; // 0-9
        int batchSize = 64;

        MultiLayerNetwork network = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
            .seed(123)
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
            .gradientNormalization(GradientNormalization.RenormalizeL2PerLayer)
            .l2(0.0001)
            .list()
            .layer(0, new DenseLayer.Builder()
                    .nIn(inputSize)
                    .nOut(500)
                    .weightInit(WeightInit.XAVIER)
                    .activation(Activation.RELU)
                    .build())
            .layer(1, new DenseLayer.Builder()
                    .nIn(500)
                    .nOut(100)
                    .weightInit(WeightInit.XAVIER)
                    .activation(Activation.RELU)
                    .build())
            .layer(2, new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
                    .nIn(100)
                    .nOut(outputSize)
                    .weightInit(WeightInit.XAVIER)
                    .activation(Activation.SOFTMAX)
                    .build())
            .backpropType(BackpropType.Standard)
            .build());
            network.init();
            network.setListeners(new ScoreIterationListener(10));

	String trainPath = "D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png";
	int numLabels = 10;

	DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12345);

	Java2DNativeImageLoader loader = new Java2DNativeImageLoader(28, 28, 1);
	File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
	BufferedImage image = ImageIO.read(imageFile);
	INDArray imageArray = loader.asMatrix(image);
	imageArray.divi(255);
        
       	int numEpochs = 10;
	for (int i = 0; i < numEpochs; i++) {
            network.fit(mnistTrain);
	}
 
        INDArray output = network.output(imageArray);
	int predictedLabel = Nd4j.argMax(output, 1).getInt(0);
	System.out.println("Predicted label: " + predictedLabel);
    }
}
