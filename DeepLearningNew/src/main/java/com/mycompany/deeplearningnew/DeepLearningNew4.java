package com.mycompany.deeplearningnew;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.Layer;
import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.tensorflow.framework.DataType;
/**
 *
 * @author JRoque
 */
public class DeepLearningNew4 {
    public static void main(String[] args) throws IOException {
        ComputationGraphConfiguration config = new NeuralNetConfiguration.Builder()
            .seed(123)
            .updater(new Adam(1e-3))
            .weightInit(WeightInit.XAVIER)
            .graphBuilder()
            .addInputs("input")
            .setInputTypes(InputType.convolutionalFlat(28,28,1))
            .addLayer("cnn1", new ConvolutionLayer.Builder(new int[]{5, 5}, new int[]{1, 1}, new int[]{0, 0}).nIn(1).nOut(20).activation(Activation.RELU).build(), "input")
            .addLayer("sub1", new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX, new int[]{2,2}).build(), "cnn1")
            .addLayer("cnn2", new ConvolutionLayer.Builder(new int[]{5, 5}, new int[]{1, 1}, new int[]{0, 0}).nOut(50).activation(Activation.RELU).build(), "sub1")
            .addLayer("sub2", new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX, new int[]{2,2}).build(), "cnn2")
            .addLayer("dense", new DenseLayer.Builder().nOut(500).activation(Activation.RELU).build(), "sub2")
            .addLayer("output", new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD).nOut(10).activation(Activation.SOFTMAX).build(), "dense")
            .setOutputs("output")
            .build();
        
        ComputationGraph network = new ComputationGraph(config);
	network.init(); 

        int batchSize = 64;
        int numEpochs = 10;
        DataSetIterator trainIterator = new MnistDataSetIterator(batchSize, true, 12345);

        for (int i = 0; i < numEpochs; i++) {
            network.fit(trainIterator);
        }

        File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
        BufferedImage image = ImageIO.read(imageFile);
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        INDArray input = Nd4j.createFromArray(pixels).reshape(1, 28, 28, 1); //.cast(DataType.DT_FLOAT);
        INDArray output = network.outputSingle(input);
        int predictedLabel = Nd4j.argMax(output, 1).getInt(0);
        System.out.println("Predicted label: " + predictedLabel);
    }
}