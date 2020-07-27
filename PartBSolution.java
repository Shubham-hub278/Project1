package com.company;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class SiteStats {
    private String url;
    private int numVisits;

    /**
     * Constructor for the SiteStats class
     *
     * @param url
     *            String that represents an URL that the user has visited
     * @param numVisits
     *            An int that represents the number of times that the user has
     *            visited the url
     */
    public SiteStats(String url, int numVisits) {
        this.url = url;
        this.numVisits = numVisits;
    }

    /**
     * This method returns the number of times that the user has visited the url.
     *
     * @return An int that represents the number of times that the user has visited
     *         the url
     */
    public int getNumVisits() {
        return this.numVisits;
    }

    /**
     * This method returns the url that we are currently tracking
     *
     * @return A String that represents the url that we are currently tracking
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method updates the number of times that we have visited the url
     *
     * @param an
     *            int that represents the number that we want to set numVisits to
     */
    public void setNumVisits(int updatedNumVisits) {
        this.numVisits = updatedNumVisits;
    }

    public String toString() {
        return this.url + " | " + this.numVisits;
    }

}

public class PartBSolution {

    private static Queue<SiteStats> sites = new LinkedList<SiteStats>();


    // Main method to list top n visited sites
    public static void listTopVisitedSites(Queue<SiteStats> sites, int n) {
        SiteStats currentMax;
        int p=sites.size(); //size of the sites queue
        //if borrowsing history is empty
        if(p==0){
         System.out.println(sites);
        }
        else
        for(int j=0;j<p-1;j++) {
            currentMax = sites.remove();   //removing first element
            for(int i = 0; i <=p-j; i++) {
                if(currentMax.getNumVisits() <= sites.peek().getNumVisits())  //comparing number of visits at first two sites
                {
                    sites.add(currentMax);    //adding site which has low number of visits
                    currentMax =sites.remove();  //removing max visit site
                }
                else {
                    SiteStats tmp = sites.remove();
                    sites.add(tmp);
                }
            }
            sites.add(currentMax);
        }
        int count=0;
        while(!sites.isEmpty()&&count<n) {
            System.out.println(sites.remove().toString());
            count++;
        }
    }

    // Method to find the website in the queue and increment the visited count by 1, adding new node in case website is not found
    public static void updateCount(String url) {
        boolean flag=true;
          for(SiteStats s:sites){
              if(url==s.getUrl()) {
                  s.setNumVisits(s.getNumVisits() + 1);
                  flag=false;
                  break;
              }
          }
          if(flag==true)
        sites.add(new SiteStats(url,1));
    }

    public static void main(String[] args) {
        String[] visitedSites = { "www.google.co.in", "www.google.co.in", "www.facebook.com", "www.upgrad.com", "www.google.co.in", "www.youtube.com",
                "www.facebook.com", "www.upgrad.com", "www.facebook.com", "www.google.co.in", "www.microsoft.com", "www.9gag.com", "www.netflix.com",
                "www.netflix.com", "www.9gag.com", "www.microsoft.com", "www.amazon.com", "www.amazon.com", "www.uber.com", "www.amazon.com",
                "www.microsoft.com", "www.upgrad.com" };

      for (String url : visitedSites) {
         updateCount(url);
        }

       listTopVisitedSites(sites, 5);

    }

}
