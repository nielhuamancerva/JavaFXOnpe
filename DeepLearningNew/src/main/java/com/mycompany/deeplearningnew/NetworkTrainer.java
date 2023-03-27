/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deeplearningnew;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import static org.deeplearning4j.nn.conf.NeuralNetConfiguration.mapper;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ActivationLayer;
import org.deeplearning4j.nn.conf.layers.BatchNormalization;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.schedule.MapSchedule;
import org.nd4j.linalg.schedule.ScheduleType;
import org.nd4j.linalg.schedule.StepSchedule;
import static play.libs.Json.mapper;
        /*
import java.io.File;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.records.listener.impl.LogRecordListener;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.BaseImageLoader;
import org.datavec.image.loader.ImageLoader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.schedule.MapSchedule;
import org.nd4j.linalg.schedule.ScheduleType;
import org.nd4j.linalg.schedule.StepSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/
/**
 *
 * @author JRoque
 */
public class NetworkTrainer {
    //private static final Logger log = LoggerFactory.getLogger(NetworkTrainer.class);
        public static void main(String[] args) throws Exception {
            // Definir parámetros de la red neuronal
        int height = 28; // altura de las imágenes de entrada
        int width = 28; // anchura de las imágenes de entrada
        int channels = 1; // número de canales de las imágenes (1 para imágenes en escala de grises, 3 para imágenes en color RGB)
        int numEpochs = 1; // número de épocas de entrenamiento
        int batchSize = 64; // tamaño del lote de entrenamiento
        int numOutputs = 10; // número de clases de salida (dígitos del 0 al 9)

        // Definir la arquitectura de la red neuronal
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(12345)
            //.updater(new Adam(new MapSchedule(ScheduleType.ITERATION, new StepSchedule(1e-3, 0.5, 10000),
            //    new StepSchedule(1e-4, 0.1, 50000),
            //    new StepSchedule(1e-5, 0.01, 100000),
            //    new StepSchedule(1e-6, 0.005, 150000),
            //    new StepSchedule(1e-7, 0.001, 200000),
            //    new StepSchedule(1e-8, 0.0005, 250000))))*/
            .weightInit(WeightInit.XAVIER)
            .gradientNormalization(GradientNormalization.RenormalizeL2PerLayer)
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
            .layer(new DenseLayer.Builder().activation(Activation.RELU)
                    .nOut(500)
                    .build())
            .layer(new BatchNormalization())
            .layer(new ActivationLayer(Activation.RELU))
            .layer(new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                    .nOut(numOutputs)
                    .activation(Activation.SOFTMAX)
                    .build())
            .setInputType(InputType.convolutionalFlat(height, width, channels))
            .build();

            // Crear la red neuronal
            MultiLayerNetwork net = new MultiLayerNetwork(conf);
            net.init();

            // Cargar los datos de entrenamiento
            DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize, true, 12345);

            // Configurar la red neuronal para el entrenamiento
            net.setListeners(new ScoreIterationListener(10)); // para imprimir el puntaje cada 10 iteraciones
            net.fit(mnistTrain, numEpochs);

            // Guardar la red neuronal entrenada en un archivo JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            File networkFile = new File("neural-network.json");
            String json = objectMapper.writeValueAsString(net);
            FileUtils.writeStringToFile(networkFile, json, StandardCharsets.UTF_8);
    }
}