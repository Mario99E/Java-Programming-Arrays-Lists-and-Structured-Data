
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class tester {
    public int[] testTryKeyLength(int keylen)
    {
        VigenereBreaker v=new VigenereBreaker();
        FileResource fr=new FileResource();
        int[] x=v.tryKeyLength(fr.asString(),keylen,'e');
        //System.out.println( x);
        return x;
     }
}
