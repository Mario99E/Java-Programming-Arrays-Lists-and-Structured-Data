
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    void testUniqIP()
     {
         LogAnalyzer myAnalyzer = new LogAnalyzer();
          myAnalyzer.readFile("");
          System.out.println("NUM of IPs: " + myAnalyzer.countUniqueIPs() );
     }
     void testprintAllHigherThanNum(int num)
     {
         LogAnalyzer myAnalyzer = new LogAnalyzer();
          myAnalyzer.readFile("");
          System.out.println("all higher than "+num);
          myAnalyzer.printAllHigherThanNum(num);
     }
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer myAnalyzer = new LogAnalyzer();
        myAnalyzer.readFile("");
        myAnalyzer.printAll();
        
    }
}
