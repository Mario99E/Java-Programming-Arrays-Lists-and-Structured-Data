
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
     int[] countLetters(String message)
    {
        String alpha="abcdefghijklmnopqrstuvwxyz";
        int[] counts=new int[26];
        for(int k=0;k <message.length() ;k++)
        {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex=alpha.indexOf(ch);
            if(dex != -1)
                counts[dex]++;
        }
        return counts;
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
    String breakCaesarCipher (String input)
    {
       
        int[] freqs=countLetters(input);
        int maxDex=indexOfMax(freqs);
        int dkey=(maxDex-4);
        if(maxDex<4)
            dkey=26-(4-maxDex);
        CaesarCipher cc=new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    void simpleTests()
    {
         FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc=new CaesarCipher (18);
        String s=cc.encrypt(input);
        System.out.println("encrypted:\t"+s);
        s=breakCaesarCipher(s);
        System.out.println("decrypted:\t"+s);
    }

}
