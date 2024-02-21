
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    private String alphabet ;
    private int mainKey;
    private String shiftedAlphabetUpper ;
    //private String shiftedAlphabetLower ;
    public CaesarCipher (int key)
    {
        mainKey=key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetUpper = alphabet.substring(key)+
                               alphabet.substring(0,key);
       // String shiftedAlphabetLower=shiftedAlphabetUpper.toLowerCase();

    }
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i) ;
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            //System.out.println("idx= "+idx);
            if(idx != -1){
                if(Character.isLowerCase(currChar) )
                {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar =Character.toLowerCase(shiftedAlphabetUpper.charAt(idx));
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
                else
                {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetUpper.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        String S =encrypted.toString();
        System.out.println("single key encrypt \n"+S);
        return S;
    }
    public String decrypt(String input)
    {
        CaesarCipher cc=new CaesarCipher(26-mainKey);
        
        String S=cc.encrypt(input);
        System.out.println("single key decrypt \n"+S);
        return S;
    }
    

}
