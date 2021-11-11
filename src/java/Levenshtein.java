import java.util.Arrays;

public class Levenshtein {
    private String str1;
    private String str2;
    public Levenshtein(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }
    public int distance(){


            int[][] mat = new int[str1.length() + 1][str2.length() + 1];

            for (int i = 0; i <= str1.length(); i++) {
                for (int j = 0; j <= str2.length(); j++) {
                    // If str1 is empty, all characters of str2 are inserted into str1,
                    //  which is of the only possible method of conversion with minimum operations.
                    if (i == 0) {
                        mat[i][j] = j;
                    }
                    // si str2 iest vide, tous les chars de str1 sont enlevés
                    // are removed, which is the only possible
                    //  method of conversion with minimum
                    //  operations.
                    else if (j == 0) {
                        mat[i][j] = i;
                    } else {

                        mat[i][j] = minm_edits(mat[i - 1][j - 1]+ NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // on remplace
                                mat[i - 1][j] + 1, // on supprime
                                mat[i][j - 1] + 1); // on insert
                    }
                }
            }

            return mat[str1.length()][str2.length()];
        }

        // check for distinct characters
        // in str1 and str2

        private static int NumOfReplacement(char c1, char c2)
        {
            return c1 == c2 ? 0 : 1;
        }

        // receives the count of different
        // operations performed and returns the
        // minimum value among them.

        private static int minm_edits(int... nums)
        {
            return Arrays.stream(nums).min().orElse(
                    Integer.MAX_VALUE);
        }
}
