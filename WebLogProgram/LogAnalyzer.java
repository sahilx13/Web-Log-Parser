
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
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     public int countUniqueIPs () {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     } 
     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int code = le.getStatusCode();
             if (code > num) {
                 System.out.println(le);
             }
         }
     }
     public ArrayList<LogEntry> uniqueIPVisitsOnDay(String someday) {
         ArrayList<LogEntry> record = new ArrayList<LogEntry>();
         ArrayList<String> ips = new ArrayList<String>();
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String date = d.toString();
             date = date.substring(4,10);
             if(someday.equals(date) && !ips.contains(le.getIpAddress())) {
                 record.add(le);
                 ips.add(le.getIpAddress());
             }
         }
         return record;
     }
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> ips = new ArrayList<String>();
         for (LogEntry le : records) {
             int code = le.getStatusCode();
             if (code >= low && code <= high && !ips.contains(le.getIpAddress())) {
                 ips.add(le.getIpAddress());
             }
         }
         return ips.size();
     }
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records) {
             String ip = le.getIpAddress();
             if(! counts.containsKey(ip)) {
                 counts.put(ip,1);
             }
             else {
                 counts.put(ip, counts.get(ip)+1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer>counts) {
         int max = -1;
         for (String ip : counts.keySet()) {
            if(counts.get(ip) > max) {
                max = counts.get(ip);
            }
         }
         return max;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer>counts) {
         int max = mostNumberVisitsByIP(counts);
         ArrayList<String> ips = new ArrayList<String>();
         for (String ip : counts.keySet()) {
             if(counts.get(ip) == max) {
                 ips.add(ip);
             }
         }
         return ips;
     }
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>(); 
         for (LogEntry le : records) {
             Date d = le.getAccessTime();
             String date = d.toString();
             date = date.substring(4,10);
             if (! myMap.containsKey(date)) {
                 ArrayList<String> ips = new ArrayList<String>();
                 ips.add(le.getIpAddress());
                 myMap.put(date, ips);
             }
             else {
                 ArrayList<String> ips = myMap.get(date);
                 //if (! ips.contains(le.getIpAddress())) {
                     ips.add(le.getIpAddress());
                     myMap.put(date, ips);
                 //}
             }
          }
          return myMap;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> myMap) {
         int max = -1;
         String day = "";
         for (String days : myMap.keySet()) {
             if (myMap.get(days).size() > max) {
                max = myMap.get(days).size();
                day = days;
             }
         }
         return day;
     }
     public ArrayList<String> iPsWithMostVisitOnDay(HashMap<String, ArrayList<String>> myMap, String date) {
        ArrayList<String> ips = myMap.get(date);
        HashMap <String, Integer> counts = new HashMap<String, Integer>();
        for(String ip : ips) {
             if(! counts.containsKey(ip)) {
                 counts.put(ip,1);
             }
             else {
                 counts.put(ip, counts.get(ip)+1);
             }
        }
        return  iPsMostVisits(counts);
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
