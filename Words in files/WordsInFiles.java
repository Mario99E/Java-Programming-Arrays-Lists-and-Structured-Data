
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String> > WordMap;
    
    public WordsInFiles()
    {
        WordMap=new HashMap<String,ArrayList<String> >();
    }
    private void addWordsFromFile (File f)
    {
        FileResource fr=new FileResource(f);
        for(String word : fr.words() )
        {
            if(WordMap.containsKey(word))
            {
                //word excist
                if(!( WordMap.get(word).contains(f.getName()) ) )
                {
                    WordMap.get(word).add(f.getName() );
                }
            }
            else
            {
                ArrayList<String> myArr=new ArrayList<String>();
                myArr.add(f.getName() );
                WordMap.put(word,myArr);
            }
        }
    }
    void buildWordFileMap ()
    {
        WordMap.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            addWordsFromFile(f);
        }
        
    }
    int maxNumber ()
    {
        int max=0;
        for(String s : WordMap.keySet() )
        {
            int n =(WordMap.get(s) ).size();
            if(max <  n)
             max=n;
        }
        return max;
    }
    ArrayList<String> wordsInNumFiles (int num)

    {
        ArrayList<String> myarr=new ArrayList<String>();
        for(String s : WordMap.keySet() )
        {
            int n =(WordMap.get(s) ).size();
            if(n ==num)
                myarr.add(s);
        }
        return myarr;
    }
    void printFilesIn (String word)
    {
        System.out.println(word+" apperd in files:");
        int ln=WordMap.get(word).size();
        for(int i=0;i<ln;i++)
        {
            System.out.println(WordMap.get(word).get(i) );
        }
    }
    void tester (int n)
    {
        buildWordFileMap();
        int mymax=maxNumber();
        System.out.println("the maximum number of files any word is in: "+mymax);
        ArrayList<String> myList= wordsInNumFiles (n);
        System.out.println("the words that apper in "+n +" files are: "+myList.size());
        for(int i=0;i<myList.size();i++)
        {
            printFilesIn(myList.get(i) );
        }
        
    }
    
    
}
