
/**
 * Find out how many times each word occurs, and
 * in particular the most frequently occurring word.
 * 
 * @author Duke Software Team
 * @version 1.0
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreqs.clear();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    private void update(String person)
    {
        int index = myWords.indexOf(person);
            if (index == -1){
                myWords.add(person);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
    }
    public void findAllCharacters (){
        FileResource resource = new FileResource();
        myWords.clear();
        myFreqs.clear();
        
        for(String s : resource.lines()){
            s = s.toLowerCase();
            int pindex=s.indexOf(".");
            if(pindex!= -1 )
                {
                    update(s.substring(0, pindex ) );
                }
        }
    }
    void charactersWithNumParts (int num1,int num2)
    {
        System.out.println( "Names between "+num1+"\t"+num2+":" );
        for (int k=0;k< myWords.size();k++)
        {
            int n=myFreqs.get(k);
            if(num1<=n && n<+num2)
            {
                System.out.println(myWords.get(k));
            }
        }
    }
    public void tester_findUnique(){
        findUnique();
       
        for(int k=0;k<myWords.size();k++)
         {
             System.out.println(myWords.get(k)+"\ttimes:"+myFreqs.get(k));
         }
        int index = findMax();
        System.out.println("max word/freq: \""+myWords.get(index)+" \" "+myFreqs.get(index));
         System.out.println("Number of unique words: "+myWords.size());
    }
    
    public void tester_findAllCharacters(){
        findAllCharacters();
        
        for(int k=0;k<myWords.size();k++)
         {
             System.out.println(myWords.get(k)+"\ttimes:"+myFreqs.get(k));
         }
        int index = findMax();
        System.out.println("main Char/freq: \""+myWords.get(index)+" \" "+myFreqs.get(index));
        System.out.println("Number of Characters: "+myWords.size());
    }
    public int findMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
