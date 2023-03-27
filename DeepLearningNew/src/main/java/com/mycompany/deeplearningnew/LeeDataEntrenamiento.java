package com.mycompany.deeplearningnew;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static org.bytedeco.opencv.global.opencv_core.BORDER_CONSTANT;
import static org.bytedeco.opencv.global.opencv_core.IPL_DEPTH_8U;
import static org.bytedeco.opencv.global.opencv_core.bitwise_not;
import static org.bytedeco.opencv.global.opencv_core.copyMakeBorder;
import static org.bytedeco.opencv.global.opencv_core.cvCreateImage;
import static org.bytedeco.opencv.global.opencv_core.cvCreateMemStorage;
import static org.bytedeco.opencv.global.opencv_core.cvGetSize;
import static org.bytedeco.opencv.global.opencv_core.cvPoint;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_THRESH_OTSU;
import static org.bytedeco.opencv.global.opencv_imgproc.cvThreshold;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;
import org.bytedeco.opencv.opencv_core.CvMemStorage;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.datavec.api.io.labels.ParentPathLabelGenerator;
import org.datavec.api.split.FileSplit;
import org.datavec.image.loader.NativeImageLoader;
import org.datavec.image.recordreader.ImageRecordReader;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.lossfunctions.LossFunctions;

/**
 *
 * @author JRoque
 */
public class LeeDataEntrenamiento {
    
    //private static MultiLayerNetwork restored;
    //private final static String IMAGEPATH = "samples/sample1.jpg";
    //private final static String[] DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    //D:\Tools-OCR\DeepLearning-4J\real-time-sudoku-solver
    //D:\Tools-OCR\DeepLearning-4J\multi-digit-segmentation-and-recognition
    
    public static void main(String[] args) throws IOException{
        
        String modelFilePath = "D:\\Tools-OCR\\DeepLearning-4J\\real-time-sudoku-solver\\cnn-model.data";

        // Carga el modelo de CNN desde el archivo
        MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(new File(modelFilePath));
        
        String imagePath = "D:\\Tools-OCR\\DeepLearning-4J\\mnist_png\\tres.png";

        // Carga la imagen de entrada y la convierte en una matriz INDArray
        NativeImageLoader loader = new NativeImageLoader(28, 28, 3);
        INDArray input = loader.asMatrix(new File(imagePath));

        // Normaliza los valores de píxel de la imagen de entrada
        input = input.div(255);

        // Realiza la predicción utilizando el modelo de CNN cargado
        INDArray output = model.output(input);

        // Muestra los resultados de la predicción
        System.out.println(output);
        
    }
}