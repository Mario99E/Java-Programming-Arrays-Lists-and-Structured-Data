
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo  {
    private String alphabet ;
    private int mainKey1;
    private int mainKey2;
    private int mainDKey1;
    private int mainDKey2;
    private String shiftedAlphabetUpper1 ;
      private String shiftedAlphabetUpper2 ;
    public CaesarCipherTwo  (int key1,int key2)
    {
        mainKey1=key1;
        mainKey2=key2;
        mainDKey1=26-key1;
        mainDKey2=26-key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetUpper1 = alphabet.substring(key1)+
                               alphabet.substring(0,key1);
         shiftedAlphabetUpper2 = alphabet.substring(key2)+
                               alphabet.substring(0,key2);
       // String shiftedAlphabetLower=shiftedAlphabetUpper.toLowerCase();

    }
    String encrypt  (String input)
    {
        CaesarCipher cc1=new CaesarCipher(mainKey1);
        CaesarCipher cc2=new CaesarCipher(mainKey2);
        
        StringBuilder enc1= new StringBuilder(cc1.encrypt(input));
        StringBuilder enc2= new StringBuilder(cc2.encrypt(input));
        StringBuilder encrypted = new StringBuilder(input);
        char mych;
        for(int i=0;i< input.length() ;i++)
        {
            if(i%2==0)
             mych=enc1.charAt(i);
            else
              mych=enc2.charAt(i);
            encrypted.setCharAt(i,mych);
        }
        String S =encrypted.toString();
        System.out.println("two key encrypt \n"+S);
        return S;
    }
    String decrypt  (String input)
    {
       
       CaesarCipher cc1=new CaesarCipher(mainDKey1);
        CaesarCipher cc2=new CaesarCipher(mainDKey2);
        
        StringBuilder enc1= new StringBuilder(cc1.encrypt(input));
        StringBuilder enc2= new StringBuilder(cc2.encrypt(input));
        StringBuilder encrypted = new StringBuilder(input);
        char mych;
        for(int i=0;i< input.length() ;i++)
        {
            if(i%2==0)
             mych=enc1.charAt(i);
            else
              mych=enc2.charAt(i);
            encrypted.setCharAt(i,mych);
        }
        String S =encrypted.toString();
        System.out.println("two key decrypt \n"+S);
        return S;
        
    }

}
