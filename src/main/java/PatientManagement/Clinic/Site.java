/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

/**
 *
 * @author kal bugrara
 */
public class Site {
    String name;
    Location location;
    EventSchedule siteEventSchedule;

    public Site(Location loc, String n) {
        location = loc;
        name = n;
        siteEventSchedule = new EventSchedule();
    }

    public EventSchedule getSiteEventSchedule() {
        return siteEventSchedule;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void printShortInfo(){
        System.out.println("City location: " + location + "Site name: " + name);
    }

}
