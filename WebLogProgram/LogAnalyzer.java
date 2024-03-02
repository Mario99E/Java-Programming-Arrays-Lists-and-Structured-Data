
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr=new FileResource();
         for(String line : fr.lines() )
         {
             LogEntry myLog =WebLogParser.parseEntry(line);
             records.add(myLog);
             
         }
     }
     public int countUniqueIPs()
     {
         ArrayList<String> uniqueIPs =  new ArrayList<String>();
         for(LogEntry le : records)
             {
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPs.contains(ipAddr) )
                 {
                     uniqueIPs.add(ipAddr);
                 }
             }
            return uniqueIPs.size();
     }
     
     public void  printAllHigherThanNum(int num)
     {
         int currStat=0;
         for (LogEntry le : records) {
             currStat=le.getStatusCode();
             if(currStat>num)
             {
                 System.out.println(le);
             }
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {
         ArrayList<String> myList=new ArrayList<String>();
         for(LogEntry le : records)
             {
                 String ipAddr = le.getIpAddress();
                 String mydate=le.getAccessTime().toString();
                 if( mydate.contains(someday) )
                 {
                     if(!myList.contains(ipAddr) )
                     {
                         myList.add(ipAddr);
                     }
                     
                 }
                 
             }
         return myList;
     }
     int countUniqueIPsInRange (int low,int high)
     {
          ArrayList<String> uniqueIPs =  new ArrayList<String>();
         for(LogEntry le : records)
             {
                 int currStat=le.getStatusCode();
                 if ( currStat<=high && currStat>= low )
                 {
                      String ipAddr = le.getIpAddress();
                         if(!uniqueIPs.contains(ipAddr) )
                         {
                             uniqueIPs.add(ipAddr);
                         }
                 }
                
             }
            return uniqueIPs.size();
     }
     HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> counts=new HashMap<String,Integer>();
         for(LogEntry le : records)
             {
               
                 
                      String ipAddr = le.getIpAddress();
                         if(! counts.containsKey(ipAddr) )
                         {
                             counts.put(ipAddr,1);
                         }
                         else
                         {
                              counts.put(ipAddr,counts.get(ipAddr)+1);
                         }
                 
                
             }
         
         return counts;
     }
     int mostNumberVisitsByIP ( HashMap<String,Integer> counts)
     {
         int max =0;
         for( String s : counts.keySet() )
         {
             if(counts.get(s) > max )
                 max=counts.get(s);
         }
         return max;
     }
     ArrayList<String> iPsMostVisits ( HashMap<String,Integer> counts)
     {
         ArrayList<String> arr = new ArrayList<String>();
         int max =mostNumberVisitsByIP(counts);
         for( String s : counts.keySet() )
         {
             if(counts.get(s) == max )
                 arr.add(s);
         }
         return arr;
     }
     
     HashMap<String,ArrayList<String> > iPsForDays()
     {
         HashMap<String,ArrayList<String> > map=new HashMap<String,ArrayList<String> >();
         for(LogEntry le : records)
             {
                 String ipAddr = le.getIpAddress();
                 String mydate=le.getAccessTime().toString().substring(4,10);
                 //System.out.println(mydate);
                 if( ! map.containsKey(mydate) )
                 {
                     ArrayList<String> arr= new ArrayList<String>();
                     arr.add(ipAddr);
                     map.put(mydate,arr);
                 }
                 else
                 {
                     map.get(mydate).add(ipAddr);
                 }
                     
             }
          //  System.out.println(map);     
         return map;
     }
     String dayWithMostIPVisits(HashMap<String,ArrayList<String> > map)
     {
         int max=0;
         String day="";
         for (String s : map.keySet() )
         {
             int num=map.get(s).size(); 
             if( num> max)
             {
                 max=num;
                 day=s;
             }
         }
         return day;
     }
     ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String> > map,String day)
     {
         ArrayList<String> myDay= map.get(day);
         HashMap <String,Integer> counts=new HashMap <String,Integer>();
         for(int i=0 ; i<myDay.size() ; i++ )
         {
             String ipAddr = myDay.get(i);
                         if(! counts.containsKey(ipAddr) )
                         {
                             counts.put(ipAddr,1);
                         }
                         else
                         {
                              counts.put(ipAddr,counts.get(ipAddr)+1);
                         }
         }
         int max=mostNumberVisitsByIP(counts);
         System.out.println(max);
         System.out.println(counts);
         ArrayList<String> res=new ArrayList<String>();
         for(String s : counts.keySet() )
         {
        
             if(max == counts.get(s))
                 res.add(s);
         }
         return res;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
