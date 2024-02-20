
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
  boolean   isVowel (char ch)
  {
      boolean res=false;
      ch=Character.toLowerCase(ch);
      String vowels="aeiou";
      if( vowels.contains(Character.toString(ch)) )
          res=true;
      return res;
      
  }
  String replaceVowels (String phrase,char ch)
  {
      StringBuilder myS=new StringBuilder(phrase);
      char currCh;
      for(int i=0;i<phrase.length();i++)
      {
          currCh=myS.charAt(i);
          if(isVowel(currCh))
              {
                  myS.setCharAt(i,ch);
              }
      }
      return myS.toString();
  }
  String emphasize (String phrase,char ch)
  {
      StringBuilder myS=new StringBuilder(phrase);
      char currCh;
      for(int i=0;i<phrase.length();i++)
      {
          currCh=myS.charAt(i);
          if(Character.toLowerCase(currCh) == Character.toLowerCase(ch))
              {
                  if(i%2==0)
                      myS.setCharAt(i,'*');
                  else
                      myS.setCharAt(i,'+');
              }
      }
      return myS.toString();
  }
}
