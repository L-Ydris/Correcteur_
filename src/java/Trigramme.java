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
                    if (Character.toString(word.charAt(i)).contains("<") | Character.toString(word.charAt(i+2)).contains(">")) continue;
                    trigrammes[i]=(Character.toString(word.charAt(i))+word.charAt(i+1)+word.charAt(i+2)); // on crée les trigrammes associés au dico
                }
                ArrayList<String> words=new ArrayList<>();
                words.add(word);
                for (int i = 0; i < trigrammes.length; i++) {
                    if (!dictionnaire.containsKey(trigrammes[i])){

                        dictionnaire.put(trigrammes[i],words);
                    } else{
                        dictionnaire.get(trigrammes[i]).add(word);
                    }
                }
            }

            String[] trigrammes = new String[this.str.length()-2];

            for (int i = 0; i < this.str.length()-2; i++) {

                trigrammes[i]=(Character.toString(this.str.charAt(i))+this.str.charAt(i+1)+this.str.charAt(i+2));
            }


            Map<String,Integer> ressemblances= new HashMap<String, Integer>();
            ArrayList<String> motsTestes= new ArrayList<String>();
            ArrayList<Integer> trigrammesCommuns=new ArrayList<Integer>();

            for (int i = 0; i < trigrammes.length; i++) {
                ArrayList<String> temp= dictionnaire.get(trigrammes[i]);
                if(temp!=null){
                    for (String str: temp) {if(!motsTestes.contains(str)){
                        motsTestes.add(str);}
                    }
                    for ( String str: temp) {
                        if (!ressemblances.containsKey(str)){
                            ressemblances.put(str,1);
                        }else{
                            ressemblances.replace(str,ressemblances.get(str)+1);
                        }
                    }}

            }
            for (String lemot: motsTestes) {
                trigrammesCommuns.add(ressemblances.get(lemot));
            }


            int min =0,min2 =0,min3=0;
            for (Integer nbr:trigrammesCommuns){
                if(nbr>min)min=nbr;

                if(nbr>min2 && nbr<min) min2=nbr;

                if(nbr > min3 && nbr < min2) min3=nbr;
            }




            ArrayList<String> candidatsFinals = new ArrayList<String>(this.CandidatFinal(motsTestes,ressemblances,min,min2,min3));


            ArrayList<Integer> distances=new ArrayList<Integer>();

            for (String candidat:candidatsFinals) {

                    Levenshtein levenshtein = new Levenshtein(this.str, candidat);
                    distances.add(levenshtein.distance());

            }

            int minDistance = 500;//un nombre très grand

            for (Integer ladistance:distances) {
                if(minDistance>ladistance){
                    minDistance=ladistance;
                }
            }
            System.out.println(minDistance);
            for (String candidat :candidatsFinals) {
                Levenshtein levenshtein = new Levenshtein(this.str,candidat);
                if(levenshtein.distance()==minDistance){
                    return str;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return str;
    }



    private ArrayList<String> CandidatFinal(ArrayList<String> motsTestes,Map<String,Integer> ressemblances,int min,int min2, int min3){
        ArrayList<String> candidatsFinals = new ArrayList<String>();
        for (String lemot:motsTestes) {
            if(ressemblances.get(lemot)==min || ressemblances.get(lemot)==min2 || ressemblances.get(lemot)==min3)
                candidatsFinals.add(lemot);
        }
        return candidatsFinals;
    }
}
