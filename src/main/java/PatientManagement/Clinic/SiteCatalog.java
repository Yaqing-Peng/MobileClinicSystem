/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class SiteCatalog {

    ArrayList<Site> sites;

    public SiteCatalog() {
        sites = new ArrayList<Site>();
    }

    public Site newSite(Location loc, String n) {
        Site s = new Site(loc,n);
        sites.add(s);
        return s;
    }

    public ArrayList<Site> getSiteListByLocation(Location loc){
        ArrayList<Site> sitesOfSameLocation = new ArrayList<Site>();
       for(Site s: sites){
            if(s.getLocation().getName().equals(loc.getName())){
                sitesOfSameLocation.add(s);
            }
       }
        return sitesOfSameLocation;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the site catalog.");
        System.out.println("There are " + sites.size() + " sites.");
        for (Site site: sites){
            site.printShortInfo();
        }
        System.out.println("-----------------------------------------------");
        System.out.println("                                              ");
    }
}
