package com.mycompany.deeplearningnew;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DropoutLayer;
import org.deeplearning4j.nn.conf.layers.PoolingType;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
/**
 *
 * @author JRoque
 */
public class DeepLearningNew6 {
    public static void main(String[] args) throws IOException {int numRows = 28;
        int numColumns = 28;
        int numChannels = 1;
        int outputNum = 10;
        int batchSize = 64;
        int iterations = 1;
        int seed = 123;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                //.iterations(iterations)
                //.regularization(true).l2(0.0001)
                .activation(Activation.RELU)
                //.learningRate(0.02)
                .weightInit(WeightInit.XAVIER)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Nesterovs(0.9))
                .list()
                .layer(0, new ConvolutionLayer.Builder(5, 5)
                        .nIn(numChannels)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.IDENTITY)
                        .build())
                .layer(1, new SubsamplingLayer.Builder(PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(2, new ConvolutionLayer.Builder(5, 5)
                        .stride(1, 1)
                        .nOut(50)
                        .activation(Activation.IDENTITY)
                        .build())
                .layer(3, new SubsamplingLayer.Builder(PoolingType.MAX)
                        .kernelSize(2,2)
                        .stride(2,2)
                        .build())
                .layer(4, new DenseLayer.Builder().activation(Activation.RELU)
						.nOut(500).build())
						.layer(5, new DropoutLayer.Builder(0.5).build())
						.layer(6, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
						.nOut(outputNum)
						.activation(Activation.SOFTMAX)
						.build())
						.setInputType(InputType.convolutionalFlat(numRows,numColumns,numChannels))
						//.backprop(true).pretrain(false)
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));


        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize,true,12345);

        DataNormalization normalizer = new NormalizerStandardize();
        normalizer.fit(mnistTrain);
        mnistTrain.setPreProcessor(normalizer);

        for (int i = 0; i < 10; i++) {
                model.fit(mnistTrain);
                System.out.println("Completed epoch " + i);
        }

        File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
        BufferedImage image = ImageIO.read(imageFile);

        // Convertir la imagen a una matriz INDArray
        int[] shape = new int[]{1, numChannels, numRows, numColumns};
        INDArray input = Nd4j.create(shape);
        for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numColumns; j++) {
                        int pixel = image.getRGB(j, i);
                        // La imagen está en escala de grises, por lo que los valores R, G y B son iguales
                        float value = ((pixel & 0xff) / 255.0f);
                        input.putScalar(new int[]{0, 0, i, j}, value);
                }
        }

        // Escalar la imagen
        DataSetPreProcessor preProcessor = new ImagePreProcessingScaler(0, 1);
        preProcessor.preProcess((org.nd4j.linalg.dataset.api.DataSet) input);

        INDArray output = model.output(input);
        int predictedDigit = Nd4j.argMax(output, 1).getInt(0);
        System.out.println("La red neuronal predice que el dígito es: " + predictedDigit);
    }
}
