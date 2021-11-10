import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Trigramme {
//    private String word1;
//    private String word2;
//    private int distance = 0;
//
//    public Levenshtein(String word1, String word2){
//        this.word1 = word1;
//        this.word2 = word2;
//    }
//
//    public int distance() {
//        int distance = 0;
//        for (int i = word1.length()-1; i > 0; i--) {
//            System.out.println();
//            for (int j = word2.length()-1; j > 0; j--)
//                if (word1.charAt(i) == word2.charAt(j)) distance = distance+ 1;
//
//        }
//        return distance;
//    }
//
//
//    public void insertLetter(char letter){}
//    public void replaceLetter(char letter, char newLetter){}
//    public void removeLetter(char letter){}

    private final String str;

    public Trigramme(String str){
        this.str = str;
    }

    public String correcteur(){

        Map<String,ArrayList<String>> dictionnaire = new HashMap<>();
        try{

            FileInputStream file = new FileInputStream("minidico.txt");
            Scanner scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                String word= "<"+scanner.nextLine()+">";

                String[] trigrammes = new String[word.length()-2];

                for (int i = 0; i < word.length()-2; i++) {

                    trigrammes[i]=(Character.toString(word.charAt(i))+word.charAt(i+1)+word.charAt(i+2));
                }
                //System.out.println(trigrammes[word.length()-3]);
                for (int i = 0; i < trigrammes.length; i++) {
                    if (!dictionnaire.containsKey(trigrammes[i])){
                        ArrayList<String> words=new ArrayList<>();
                        words.add(word);
                        dictionnaire.put(trigrammes[i],words);

                    }
                    else{
                        dictionnaire.get(trigrammes[i]).add(word);
                    }
                }
            }


            String[] trigrammes = new String[this.str.length()-2];

            for (int i = 0; i < this.str.length()-2; i++) {

                trigrammes[i]=(Character.toString(this.str.charAt(i))+this.str.charAt(i+1)+this.str.charAt(i+2));
            }

            for (String tri:trigrammes) {
                //System.out.println( dictionnaire.get(tri));


            }
            Map<String,Integer> ressemblances= new HashMap<String, Integer>();
            ArrayList<String> motsTestes= new ArrayList<String>();
            ArrayList<Integer> trigrammesCommuns=new ArrayList<Integer>();
            for (int i = 0; i < trigrammes.length; i++) {
                //System.out.println(trigrammes[i]);
            }
            for (int i = 0; i < trigrammes.length; i++) {
                ArrayList<String> temp=dictionnaire.get(trigrammes[i]);
                if(temp!=null){
                    for (String str: temp
                    ) {if(!motsTestes.contains(str)){
                        motsTestes.add(str);}
                    }
                    for ( String str: temp) {
                        if (!ressemblances.containsKey(str)){
                            ressemblances.put(str,1);
                        }
                        else{
                            ressemblances.replace(str,ressemblances.get(str)+1);
                        }
                    }}

            }
            for (String str: motsTestes) {
                trigrammesCommuns.add(ressemblances.get(str));
            }


            int min =0,min2 =0,min3=0;
            for (Integer nbr:trigrammesCommuns){
                if(nbr>min)min=nbr;

                if(nbr>min2 && nbr<min) min2=nbr;

                if(nbr > min3 && nbr < min2) min3=nbr;
            }



            //System.out.println(min+" "+min2+" "+min3);
            ArrayList<String> candidatsFinals=new ArrayList<String>();
            for (String str:motsTestes) {
                if(ressemblances.get(str)==min) candidatsFinals.add(str);
                if(ressemblances.get(str)==min2) candidatsFinals.add(str);
                if(ressemblances.get(str)==min3) candidatsFinals.add(str);
            }

            //System.out.println(candidatsFinals);


            ArrayList<Integer> levenstein=new ArrayList<Integer>();
            for (String str:candidatsFinals) {
                levenstein.add(compute_Levenshtein_distanceDP(this.str,str));
            }
            int minL=levenstein.get(0);
            for (Integer nbr:levenstein) {
                if(minL>nbr){
                    minL=nbr;
                }
            }
            //System.out.println(minL);
            for (String str:candidatsFinals) {
                if(compute_Levenshtein_distanceDP(this.str,str)==minL){
                    return str;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private static int compute_Levenshtein_distanceDP(String str1, String str2)
    {
        // A 2-D matrix to store previously calculated
        // answers of subproblems in order
        // to obtain the final

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {

                // If str1 is empty, all characters of str2 are inserted into str1,
                //  which is of the only possible method of conversion with minimum operations.
                if (i == 0) {
                    dp[i][j] = j;
                }

                // If str2 is empty, all characters of str1
                // are removed, which is the only possible
                //  method of conversion with minimum
                //  operations.
                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    // find the minimum among three
                    // operations below


                    dp[i][j] = minm_edits(dp[i - 1][j - 1]
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    // check for distinct characters
    // in str1 and str2

    static int NumOfReplacement(char c1, char c2)
    {
        return c1 == c2 ? 0 : 1;
    }

    // receives the count of different
    // operations performed and returns the
    // minimum value among them.

    static int minm_edits(int... nums)
    {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
    }
}
