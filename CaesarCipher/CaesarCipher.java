import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedAlphabetLower=shiftedAlphabetUpper.toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i) ;
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                if(Character.isLowerCase(currChar) )
                {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetLower.charAt(idx);
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
        //System.out.println("single key encrypt \n"+S);
        return S;
    }
    String encryptTwoKeys (String input,int key1,int key2)
    {
        StringBuilder enc1= new StringBuilder(encrypt(input, key1));
        StringBuilder enc2= new StringBuilder(encrypt(input, key2));
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
    public void testCaesar() {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
}

