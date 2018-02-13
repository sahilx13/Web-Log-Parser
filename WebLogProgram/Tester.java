
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
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Number of Unique IPs : "+la.countUniqueIPs());
    }
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
    }
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        System.out.println("Unique IP count in range : "+la.countUniqueIPsInRange(200, 299));
    }
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer>counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("Most times an IP visited : "+la.mostNumberVisitsByIP(counts));
    }
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer>counts = la.countVisitsPerIP();
        System.out.println("Most visited IPs are : "+la.iPsMostVisits(counts));
    }
    public void testIPsWithMostVisitOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> myMap = la.iPsForDays();
        System.out.println("IPs with mosts visits on the given day : "+la.iPsWithMostVisitOnDay(myMap,"Sep 29"));
    }
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> myMap = la.iPsForDays();
        System.out.println("IPs segregated along days : "+myMap);
        System.out.println("Day with most IP visits : "+la.dayWithMostIPVisits(myMap));
    }
}
