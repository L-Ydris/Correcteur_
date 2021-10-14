import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    String file;

    public ReadFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void reading() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(getFile()));
    }
}
