import java.util.ArrayList;

public class Levenshtein {
    private String word1;
    private String word2;
    private ArrayList<Character> Char = new ArrayList<Character>();
    
    public Levenshtein(String word1, String word2){
        this.word1 = word1;
        this.word2 = word2;
    }

    public void algo() {
        for (int i = word1.length()-1; i > 0; i--) {
            System.out.println();
            for (int j = word2.length()-1; j > 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)){
                    word1.charAt(i);
                }
            }
        }
    }

    public void insertLetter(char letter){}
    public void replaceLetter(char letter, char newLetter){}
    public void removeLetter(char letter){}
}
