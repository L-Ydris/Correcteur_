import java.util.Arrays;

public class Levenshtein {
    private String str1;
    private String str2;
    public Levenshtein(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }
    public int distance(){
            // A 2-D matrix to store previously calculated
            // answers of subproblems in order
            // to obtain the final

            int[][] mat = new int[str1.length() + 1][str2.length() + 1];

            for (int i = 0; i <= str1.length(); i++) {
                for (int j = 0; j <= str2.length(); j++) {
                    // If str1 is empty, all characters of str2 are inserted into str1,
                    //  which is of the only possible method of conversion with minimum operations.
                    if (i == 0) {
                        mat[i][j] = j;
                    }
                    // If str2 is empty, all characters of str1
                    // are removed, which is the only possible
                    //  method of conversion with minimum
                    //  operations.
                    else if (j == 0) {
                        mat[i][j] = i;
                    }
                    else {
                        // find the minimum among three
                        // operations below
                        mat[i][j] = minm_edits(mat[i - 1][j - 1]
                                        + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                                mat[i - 1][j] + 1, // delete
                                mat[i][j - 1] + 1); // insert
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
