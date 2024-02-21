
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    String halfOfString (String message,int start)
    {
        StringBuilder S=new StringBuilder();
        for(int i=start;i<message.length();i+=2)
        {
            S.append( message.charAt(i) );
        }
        return S.toString();
    }
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
    int getKey(String s)
    {
        int[] freqs=countLetters( s);
        int maxDex=indexOfMax(freqs);
        int dkey=(maxDex-4);
        if(maxDex<4)
            dkey=26-(4-maxDex);
        return (26-dkey);
    }
     String breakCaesarCipher (String input)
    {
       String half1=halfOfString(input,0),
                half2=halfOfString(input,1);
        int key1=26-getKey(half1);
        int key2=26-getKey(half2);
        System.out.println("KEY1: " +key1+"\tKEY2 :"+key2);
        CaesarCipherTwo cc=new CaesarCipherTwo(key1,key2);
        return cc.decrypt(input);
    }
    void simpleTest()
    {
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipherTwo cc=new CaesarCipherTwo (17,3);
        String s=cc.encrypt(input);
        System.out.println("encrypted:\t"+s);
        s=breakCaesarCipher(s);
        System.out.println("decrypted:\t"+s);
    }

}
