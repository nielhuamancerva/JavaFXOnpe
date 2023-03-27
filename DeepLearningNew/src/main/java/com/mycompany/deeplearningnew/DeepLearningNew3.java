package com.mycompany.deeplearningnew;

import java.io.File;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import static org.deeplearning4j.util.NetworkUtils.output;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.lossfunctions.LossFunctions;
/**
 *
 * @author JRoque
 */
public class DeepLearningNew3{
    public static void main(String[] args) throws Exception {
        // Configuración de la red neuronal
        int numInput = 784; // 28x28
        int numOutputs = 10;
        int numHiddenNodes = 300;
        double learningRate = 0.1;

        MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
            .seed(123) // fijar la semilla para reproducibilidad
            //.learningRate(learningRate)
            .list()
            .layer(new DenseLayer.Builder()
                .nIn(numInput)
                .nOut(numHiddenNodes)
                .activation(Activation.RELU)
                .build())
            .layer(new DenseLayer.Builder()
                .nIn(numHiddenNodes)
                .nOut(numHiddenNodes)
                .activation(Activation.RELU)
                .build())
            .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                .nIn(numHiddenNodes)
                .nOut(numOutputs)
                .activation(Activation.SOFTMAX)
                .build())
            .build();

        MultiLayerNetwork model = new MultiLayerNetwork(configuration);
        model.init();

        // Entrenamiento
        int numEpochs = 10;
        MnistDataSetIterator iterator = new MnistDataSetIterator(100, true, 12345);
        for (int i = 0; i < numEpochs; i++) {
            while (iterator.hasNext()) {
                DataSet dataSet = iterator.next();
                model.fit(dataSet);
            }
            iterator.reset();
            System.out.println("Epoch " + i + " complete");
        }

        // Prueba con una imagen personalizada
        // TODO: cargar la imagen y preprocesarla
        
        File imageFile = new File("D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\training\\8\\17.png");
        NativeImageLoader loader = new NativeImageLoader(28, 28, 1);
        INDArray image = loader.asRowVector(imageFile);
        
        //INDArray input = new INDArray();// = // imagen preprocesada como INDArray de forma (1, 784)
        INDArray output = model.output(image);
        System.out.println("Predicted digit: " + output.argMax().getInt(0));
    }
}