import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Trigramme {

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




            ArrayList<String> candidatsFinals=new ArrayList<String>();
            for (String str:motsTestes) {
                if(ressemblances.get(str)==min) candidatsFinals.add(str);
                if(ressemblances.get(str)==min2) candidatsFinals.add(str);
                if(ressemblances.get(str)==min3) candidatsFinals.add(str);
            }



            ArrayList<Integer> Distanceslevenstein=new ArrayList<Integer>();

            for (String candidat:candidatsFinals) {
                Levenshtein levenshtein = new Levenshtein(this.str,candidat);
                Distanceslevenstein.add(levenshtein.distance());
            }
            int minL=Distanceslevenstein.get(0);
            for (Integer nbr:Distanceslevenstein) {
                if(minL>nbr){
                    minL=nbr;
                }
            }
            //System.out.println(minL);
            for (String candidat :candidatsFinals) {
                Levenshtein levenshtein = new Levenshtein(this.str,candidat);
                if(levenshtein.distance()==minL){
                    return str;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
