import java.util.ArrayList;

public class Levenshtein {
    private String str1;
    private String str2;

    public Levenshtein(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }

    public void algo() {
        for (int i = str1.length()-1; i > 0; i--) {
            System.out.println(str1.charAt(i));
            for (int j = str2.length()-1; j > 0; j--) {
                str2.charAt(j);
            }
        }
    }
}
