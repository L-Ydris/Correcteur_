
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) throws FileNotFoundException {
//        //Levenshtein levenshtein = new Levenshtein("heelooo","helloqs");
//        //levenshtein.distance();
//
//    }

    public static void main(String[] args) {

        ArrayList<String> ListeFautes=new ArrayList<String>();

        try{
            FileInputStream file = new FileInputStream("minidico.txt");
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                ListeFautes.add(scanner.nextLine());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        long time1=System.nanoTime();

        for (String str: ListeFautes) {

            Trigramme trigramme = new Trigramme(str);
            trigramme.correcteur();
        }

        long time2=System.nanoTime();
        //moyenne=moyenne+(time2-time1);

        System.out.println("la correction des mots a mis "+(time2-time1)+" nanoseconds");
    }

}
