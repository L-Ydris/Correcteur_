
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ReadFile readFile = new ReadFile("fautes.txt");

        ArrayList<String> Fautes=new ArrayList<String>(readFile.reading());

        long time1=System.nanoTime();

        for (String faute: Fautes) {
            Trigramme trigramme = new Trigramme(faute);
            String lemotcorrigé = trigramme.correcteur();
        }

        long time2=System.nanoTime();

        System.out.println("la correction des mots a durée "+(time2-time1)*Math.pow(0.1,9) +" secondes");
    }

}
