import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder s=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices)
        {
            s.append(message.charAt(i) );
        }
        return s.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i=0; i<klength ;  i++)
        {
            String s=sliceString(encrypted,i,klength);
            CaesarCracker CB =new CaesarCracker(mostCommon);
            key[i]=CB.getKey(s);
        }
        return key;
    }
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set=new HashSet<String>();
        for(String line : fr.lines() )
        {
            set.add(line.toLowerCase() ); 
        }
        return set;
    }
    public HashSet<String> readDictionary()
    {
        HashSet<String> set=new HashSet<String>();
        FileResource fr=new FileResource();
        for(String line : fr.lines() )
        {
            set.add(line.toLowerCase() ); 
        }
        return set;
    }
    int countWords(String message,HashSet<String> dictionary)
    {
        String[] words=message.split("\\W+");
        int n=0;
        for(int i=0;i<words.length;i++)
        {
            String s=words[i].toLowerCase();
            if(dictionary.contains(s))
                n++;
        }
        return n;
    }
    public String breakForLanguage(String encrypted,HashSet<String> dictionary)
    {
        int max=0;
        int bestKey=0;
        char commonChar=mostCommonCharIn(dictionary);
        for(int i=1;i<=100 ; i++)
        {
            int[] key=tryKeyLength(encrypted,i,commonChar);
            VigenereCipher V=new VigenereCipher(key);
            String decrypted=V.decrypt(encrypted) ;
            int validNum=countWords(decrypted,dictionary);
            if(validNum>max)
            {
                max=validNum;
                bestKey=i;
            }
        }
        int[] fkey=tryKeyLength(encrypted,bestKey,commonChar);
        VigenereCipher V=new VigenereCipher(fkey);
        String fdecrypted=V.decrypt(encrypted) ;
        System.out.println("best key len: "+bestKey);
        System.out.println("Valid Words: "+max);
        return fdecrypted;
    }
    char mostCommonCharIn(HashSet<String> set)
    {
        HashMap<Character,Integer> charCounts=new HashMap<Character,Integer>();
        //coutn chars
        for(String s : set)
        {
            for(int i=0 ;i<s.length() ;i++)
            {
                char ch=s.charAt(i);
                if(charCounts.containsKey(ch) )
                {
                    charCounts.put(ch, charCounts.get(ch) +1 );
                }
                else
                {
                    charCounts.put(ch,1);
                }
            }
        }
        char commonChar='e';
        for(char ch : charCounts.keySet())
        {
            if(charCounts.get(commonChar) < charCounts.get(ch))
            {
                commonChar=ch;
            }
        }
        return commonChar;
    }
    void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages )
    {
        String myLan="";
        String msg="";
        int max=0;
        for(String lan:languages.keySet() )
        {
            System.out.println(lan);
            String decrypt =breakForLanguage(encrypted,languages.get(lan));
            int validNum=countWords(decrypt,languages.get(lan) );
            if(max<validNum)
            {
                max=validNum;
                msg=decrypt;
                myLan=lan;
            }
        }
        System.out.println("the valid lnaguage "+myLan+" with "+max);
        System.out.println(msg);
    }
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        //dictionayr
        FileResource dfr=new FileResource();
        HashSet<String> dictionay=readDictionary(dfr);
        int[] key=tryKeyLength(encrypted,4,'e');
        //System.out.println(breakForLanguage(encrypted,dictionay) );
        System.out.println(breakForLanguage(encrypted,dictionay).split("\n")[0] );
    }
     public void breakVigenereForAllLanguages () {
        //WRITE YOUR CODE HERE
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        //dictionayr
        HashMap<String,HashSet<String>> languages=new  HashMap<String,HashSet<String>>();
        DirectoryResource dr=new DirectoryResource();
        for(File F: dr.selectedFiles())
        {
            FileResource dfr=new FileResource(F);
            HashSet<String> dictionay=readDictionary(dfr);
            languages.put(F.getName(),dictionay);
        }
        breakForAllLangs(encrypted,languages);
        
    }
    String readAndDecrypt(int keylne)
    {
        FileResource fr=new FileResource();
        String encrypted=fr.asString();
        int[] key=tryKeyLength(encrypted,keylne,'e');
        VigenereCipher V=new VigenereCipher(key);
        return V.decrypt(encrypted);
    }
    
}
