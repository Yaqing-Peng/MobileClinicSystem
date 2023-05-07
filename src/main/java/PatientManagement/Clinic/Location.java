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
public class Location {
    String name;
    SiteCatalog siteList;

    public void setSiteList(SiteCatalog siteList) {
        this.siteList = siteList;
    }

    public Location(String name) {
        this.name = name;
        siteList = new SiteCatalog();
    }

    public SiteCatalog getSiteList() {
        return siteList;
    }

    public String getName() {
        return name;
    }

    public void printShortInfo(){
        System.out.println("The location of this site is: " + name);
    }

}
