
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.rules.ZeroR;
//import weka.classifiers.meta.MultiClassClassifier;


    /*
based on: https://www.cs.umb.edu/~ding/history/480_697_spring_2013/homework/WekaJavaAPITutorial.pdf
https://stackoverflow.com/questions/33760145/weka-how-to-predict-new-unseen-instance-using-java-code
 */

/**
 *
 * @author carlatv
 */
public class wekatesting {
    /*public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader  = null;

        try{
            inputReader = new BufferedReader(new FileReader(filename));

        }catch (FileNotFoundException ex){

            System.err.println("File not found: "+ filename);

        }

        return inputReader;
    }*/

    public static void main(String[] args) throws Exception{
        //start by providing the paths for your training and testing ARFF files make sure both files have the same structure and the exact classes in the header

        //initialise classifier
        Classifier classifier = null;

        System.out.println("read training arff");

        //Instances train = new Instances(new BufferedReader(new FileReader("CorpusAligned.arff")));
        Instances train = new Instances(new BufferedReader(new FileReader("CorpusAligned_BinaryBalanced.arff")));

        train.setClassIndex(0);//número de l'atribut respecte el que volem classificar

        System.out.println("read testing arff");
        //Instances unlabeled = new Instances(new BufferedReader(new FileReader("testFile.arff")));
        Instances unlabeled = new Instances(new BufferedReader(new FileReader("sentence6.arff")));
        unlabeled.setClassIndex(0);

        //podem carregar un model ja guardat o entrenar-lo de nou:
        //classifier = (Classifier) weka.core.SerializationHelper.read("myModel.dat");
        classifier = new SMO();
        //classifier = new ZeroR();
        System.out.println("==========================================================================");
        System.out.println("training using SMO");
        //System.out.println("training using ZeroR (this is our baseline)");
        Evaluation eval = new Evaluation(train);
        //perform 10 fold cross validation
        eval.crossValidateModel(classifier, train, 10, new Random(1));
        String output = eval.toSummaryString();
        System.out.println(output);
        String classDetails = eval.toClassDetailsString();
        System.out.println(classDetails);
        classifier.buildClassifier(train);
        Instances labeled = new Instances(unlabeled);
        //System.out.println("unlabeled:"+unlabeled);

        // Creating a File object that represents the disk file.
        PrintStream o = new PrintStream(new File("output_predictor.csv"));

        // Assign o to output stream
        System.setOut(o);

        // label instances (use the trained classifier to classify new unseen instances)
       for (int i = 0; i < unlabeled.numInstances(); i++) {
            //System.out.println("i = "+i);
            double clsLabel = classifier.classifyInstance(unlabeled.instance(i));
            System.out.println("instance:"+unlabeled.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
            System.out.println("Classification:"+unlabeled.classAttribute().value((int) clsLabel)+"|");
        }


        /*try ( //save the model for future use
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("myModel.dat"))) {
                out.writeObject(classifier);
        }
            System.out.println("===== Saved model =====");

        }*/

    }
}
