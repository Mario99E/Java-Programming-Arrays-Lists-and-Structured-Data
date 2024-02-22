
/**
 * Write a description of Codon_Count here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class Codon_Count {
    private HashMap<String,Integer> DNAmap ;
    
    public Codon_Count()
    {
        DNAmap=new HashMap<String,Integer>();
    }
    public void buildCodonMap (int start,String dna)
    {
        DNAmap.clear();
        for(int i=start;(i+3)<dna.length();i+=3)
        {
            String s= dna.substring(i,i+3);
            if(DNAmap.containsKey(s) )
            {
                DNAmap.put(s,DNAmap.get(s)+1);
            }
            else
            {
                DNAmap.put(s,1);
            }
        }
        
    }
    public String getMostCommonCodon ()
    {
        int max=0;
        String maxCodon="";
        for(String s : DNAmap.keySet() )
        {
            if(max <DNAmap.get(s))
                {
                    max=DNAmap.get(s);
                    maxCodon=s;
                }
        }
        return maxCodon;
    }
    void printCodonCounts (int start,int end)
    {
       System.out.println("CODONS BETEWEEN: "+start+"\t"+end); 
        for(String s : DNAmap.keySet() )
        {
            if( start<=DNAmap.get(s) && end>=DNAmap.get(s) )
                {
                   System.out.println(s+"\t"+DNAmap.get(s));
                }
        }
    }
    void tester()
    {
        FileResource fr=new FileResource();
        String sdna=fr.asString();
        sdna=sdna.toUpperCase();
        for(int n=0;n<3;n++)
        {
            buildCodonMap(n,sdna);
            System.out.println("CASE"+n);
            System.out.println("TOTAL NUM OF UNIQUE IN FRAME: "+DNAmap.size());
            String s=getMostCommonCodon();
            System.out.println("THE MOST COMMON CODON: "+s+"\t"+DNAmap.get(s));
            printCodonCounts(1,8);
            
        }
    
        
    }

}
