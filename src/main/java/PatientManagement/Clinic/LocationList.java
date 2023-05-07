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
public class LocationList {
   
    ArrayList<Location> locations;

    public LocationList() {
        locations = new ArrayList<Location>();
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }


    public Location getLocation(String locname) {
        // search for a location object by locname;
        Location l = null;
        return l;
    }

    public Location newLocation(String name) {
        Location newLoc = new Location(name);
        locations.add(newLoc);
        return newLoc;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the locationList.");
        System.out.println("There are " + locations.size() + " locations.");
        for (Location lc: locations){
            lc.printShortInfo();
        }
        System.out.println("-----------------------------------------------");
        System.out.println("                                              ");
    }
}
