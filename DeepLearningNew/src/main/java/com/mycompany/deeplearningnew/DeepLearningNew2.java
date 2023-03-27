package com.mycompany.deeplearningnew;
/*
import com.sun.javafx.iio.ImageLoader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.annotation.Native;
import javax.imageio.ImageIO;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.DropoutLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer.PoolingType;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.LoggerFactory;

import org.nd4j.linalg.indexing.NDArrayIndex;
import org.nd4j.linalg.learning.config.Adam;
        */
/**
 *
 * @author JRoque
 */
public class DeepLearningNew2 {

private static final int SEED = 123;
    private static final int NUM_ITERATIONS = 1;
    
    public static void main(String[] args) {
        /*
        int height = 28;
        int width = 28;
        int channels = 1;
        int batchSize = 32; //64
        int outputNum = 10;
        int numEpochs = 1; //10
        double learningRate = 0.001;

        // cargar conjunto de datos de MNIST
        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12345);
        DataSetIterator mnistTest = new MnistDataSetIterator(batchSize, false, 12345);

        // crear modelo de red neuronal
        MultiLayerNetwork model = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
                //.seed(12345)
                //.updater(new Adam(learningRate))
                //.list()
                .seed(SEED).weightInit(WeightInit.XAVIER)
                //.iterations(NUM_ITERATIONS
                //.regularization(true).l2(0.0005).learningRate(.01)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                //.updater(Updater.NESTEROVS).momentum(0.9)
                .list()
                .layer(new ConvolutionLayer.Builder(5, 5)
                        .nIn(channels)
                        .stride(1, 1)
                        .nOut(20)
                        .activation(Activation.RELU)
                        .build())
                .layer(new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(new ConvolutionLayer.Builder(5, 5)
                        .stride(1, 1)
                        .nOut(50)
                        .activation(Activation.RELU)
                        .build())
                .layer(new SubsamplingLayer.Builder(SubsamplingLayer.PoolingType.MAX)
                        .kernelSize(2, 2)
                        .stride(2, 2)
                        .build())
                .layer(new DenseLayer.Builder()
                        .nOut(500)
                        .activation(Activation.RELU)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nOut(outputNum)
                        .activation(Activation.SOFTMAX)
                        .build())
                .setInputType(InputType.convolutionalFlat(height, width, channels))
                .build());
    
        // configurar modelo
        model.init();
        model.setListeners(new ScoreIterationListener(1));

        // entrenar modelo con conjunto de datos de MNIST
        for (int i = 0; i < numEpochs; i++) {
            model.fit(mnistTrain);
            Evaluation eval = model.evaluate(mnistTest);
            System.out.println("Epoch " + i + " Accuracy: " + eval.accuracy());
            mnistTrain.reset();
            mnistTest.reset();
        }

        /*
        // probar modelo con una imagen personalizada
        File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
        NativeImageLoader loader = new NativeImageLoader(height, width, channels);
        
        INDArray image = loader.asMatrix(imageFile);
        
        NormalizerMinMaxScaler scaler = new NormalizerMinMaxScaler();
        //scaler.fit(dataSet);
        //scaler.fit(image);
        scaler.transform(image);
        INDArray output = model.output(image);
        System.out.println("Prediction: " + output);
        */
        /*
        // Carga la imagen de prueba
        File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
        NativeImageLoader loader = new NativeImageLoader(28, 28, 1);
        INDArray image = loader.asRowVector(imageFile);

        // Normalización de la imagen
        DataNormalization scaler = new ImagePreProcessingScaler(0, 1);
        scaler.transform(image);

        // Predicción de la red neuronal
        INDArray output = model.output(image);
        int prediction = output.argMax(1).getInt(0);
        System.out.println("Prediction: " + prediction);
        */
    }
}