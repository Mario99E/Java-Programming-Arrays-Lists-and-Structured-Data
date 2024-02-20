
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    void countWordLengths (FileResource resource,int[] counts)
    {
        int n=0;
        for(String word : resource.words())
        {
            n=word.length();
            StringBuilder words=new StringBuilder(word);
            if( !Character.isLetter(words.charAt(n-1)) )
                n--;
            if( !Character.isLetter(words.charAt(0)) )
                n--;
            if( n < counts.length)
                counts[n]++;
            else
                counts[counts.length-1]++;
        }
    }
    int indexOfMax (int[] values)
    {
        int max=values[0] ,maxInd=0;
        for (int i=0 ;i<values.length;i++)
        {
            if(max< values[i])
            {
                max=values[i];
                maxInd=i;
            }
        }
        return maxInd;
        
    }
    void testCountWordLengths ()
    {
        FileResource fr=new FileResource();
        int[] counts=new int[31];
        countWordLengths(fr,counts);
        for(int k=0 ;k<31;k++)
        {
            if( counts[k] ==0)
                continue;
            System.out.println("length: "+k+"\t"+"num: "+counts[k]);
        }
        int max=indexOfMax(counts);
        
        System.out.println("the most common word length: " +max);
        
    }

}
