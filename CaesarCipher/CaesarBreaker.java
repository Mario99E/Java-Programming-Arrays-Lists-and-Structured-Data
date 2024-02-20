
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreaker {
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
    public String decrypt(String encrypted)
    {
        CaesarCipher cc=new CaesarCipher();
        int[] freqs=countLetters(encrypted);
        int maxDex=indexOfMax(freqs);
        int dkey=(maxDex-4);
        if(maxDex<4)
            dkey=26-(4-maxDex);
        return cc.encrypt(encrypted,26-dkey);
    }
    String halfOfString (String message,int start)
    {
        StringBuilder S=new StringBuilder();
        for(int i=start;i<message.length();i+=2)
        {
            S.append( message.charAt(i) );
        }
        return S.toString();
    }
    int getKey(String s)
    {
        CaesarCipher cc=new CaesarCipher();
        int[] freqs=countLetters( s);
        int maxDex=indexOfMax(freqs);
        int dkey=(maxDex-4);
        if(maxDex<4)
            dkey=26-(4-maxDex);
        return (26-dkey);
    }
    String decryptTwoKeys (String encrypted)
    {
        String half1=halfOfString(encrypted,0),
                half2=halfOfString(encrypted,1);
        int key1=getKey(half1);
        int key2=getKey(half2);
        System.out.println("KEY1: " +(26-key1)+"\tKEY2 :"+(26-key2));
         CaesarCipher cc=new CaesarCipher();
        String decipher=cc.encryptTwoKeys(encrypted,key1,key2);
        System.out.println("THE DECRYPTED 2KEY:\n"+decipher);
        return decipher;
        
    }
    void testDecrypt()
    {
        FileResource fr = new FileResource();
        String encipher = fr.asString();
        String decipher=decrypt(encipher);
        System.out.println("decypted massage: \n"+decipher);
    }
    void testTowKeyDecrypt()
    {
        FileResource fr = new FileResource();
        String encipher = fr.asString();
        String decipher=decryptTwoKeys(encipher);
        System.out.println("decypted massage: \n"+decipher);
    }

}
