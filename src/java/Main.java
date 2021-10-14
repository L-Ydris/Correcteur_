
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Levenshtein levenshtein = new Levenshtein("heelooo","helloqs");
        levenshtein.distance();

    }
}
