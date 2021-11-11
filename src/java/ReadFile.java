import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {

    String file;
    public ReadFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public ArrayList<String> reading(){
        ArrayList<String> ListeFautes=new ArrayList<String>();
        try{
            FileInputStream file = new FileInputStream(this.file);
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                ListeFautes.add(scanner.nextLine());
            }
            return ListeFautes;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return ListeFautes;
    }
}
