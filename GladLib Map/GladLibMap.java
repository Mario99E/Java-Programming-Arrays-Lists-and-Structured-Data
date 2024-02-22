import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    
    private HashMap<String , ArrayList<String> > myMap;
    
    private ArrayList<String> usedWords;
     private ArrayList<String> usedCat;
    
    private int num;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap=new HashMap<String , ArrayList<String> >();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap=new HashMap<String , ArrayList<String> >();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] arr={"adjective","noun","color","country",
                        "name","animal","timeframe","verb","fruit"};
        for(int i=0;i<arr.length;i++)
        {
            ArrayList<String> arrlist=readIt(source+"/"+arr[i]+".txt");
            myMap.put(arr[i],arrlist);
        }
      
        usedWords = new  ArrayList<String>();
        usedCat = new  ArrayList<String>();
        num=0;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        

       
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
        
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        num++;
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String cat=w.substring(first+1,last);
        if(!usedCat.contains(cat) )
           usedCat.add(cat);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.contains(sub) )
        {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    int totalWordsInMap ()
    {
        int n=0;
        for(String word :myMap.keySet() )
        {
            n+= (myMap.get(word).size() );
        }
        return n;
    }
    int totalWordsConsidered ()
    {
        int n=0;
        for(String word :myMap.keySet() )
        {
            if(usedCat.contains(word) )
             n+= (myMap.get(word).size() );
        }
        return n;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNum of words replaces:\t"+num);
        System.out.println("total number of words to pick form:\t"+totalWordsInMap());
        System.out.println(usedCat.size() +" cat used and total WordsConsiderd: "+totalWordsConsidered() ); 
        usedWords.clear();
        usedCat.clear();
        num=0;
    }
    


}
