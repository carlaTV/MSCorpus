
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import java.util.Random;
        

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author carlatv
 */
public class wekatesting {
    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader  = null;
        
        try{
            inputReader = new BufferedReader(new FileReader(filename));

        }catch (FileNotFoundException ex){
            
            System.err.println("File not found: "+ filename);
        
        }
        
        return inputReader;
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader reader = readDataFile("MSCorpus.arff");
        
        //definim les instàncies
        Instances data = new Instances(reader);
        reader.close();
        
        //definim en base a quin atribut hem de classificar (en aquest cas l'últim)
        data.setClassIndex(data.numAttributes()-1);
        
        String[] options = new String[1];
        options[0] = "-U";
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(data);
        
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(tree, data, 10, new Random(1));
        
        System.out.println(eval.toSummaryString("\nResults\n======\n", false));
    }
}
