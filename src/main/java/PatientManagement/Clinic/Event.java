/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Patient.Encounters.Encounter;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Event {

    String date;
    Site site;
    String budgetCode;
    ArrayList<Encounter> encounters; // encounters that day

    public Event(Site s, String bc, String dt) {
        site = s;
        budgetCode = bc;
        date = dt;
        encounters = new ArrayList<Encounter>(); // encounters done at the event/site

    }

    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }
    //Month 03
    public boolean isEventInThisMonth(String month) {
        return date.split("/")[1].equals(month);
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        if (this.date != null) {
            String[] parts = this.date.split("/");
            if (parts.length == 3) {
                return parts[1];
            }
        }
        return null;
    }
    
    public Site getSite() {
        return site;
    }

    public void addEncounter(Encounter en) {
        encounters.add(en);
    }

    public int getConfirmedTotals() { // total numer of positive cases in event at the site
        int sum = 0;
        for (Encounter e : encounters) { // check all encounter at the event for confirmed cases

            if (e.getDiagnosis().isConfirmed()) {
                sum = sum + 1;

            }
        }

        return sum;
    }

    public ArrayList<Encounter> getConfirmedEncounters() { // return the actual confirmed encounters to you can extract
                                                           // the patient objects
        ArrayList<Encounter> temp = new ArrayList<Encounter>();
        for (Encounter e : encounters) { // check all encounter at the event for confirmed cases

            if (e.getDiagnosis().isConfirmed()) {
                temp.add(e); // encounter of confirmed case to the list to be returned

            }
        }

        return temp;
    }

    public boolean isMatch(String bn) {
        if (budgetCode.equals(bn)) {
            return true;
        } else {
            return false;
        }
    }

    public void printShortInfo(){
        System.out.println("Info of this event:"+ " | " + "Date: " + date + " | " +  "Site: " + site.name + " | " + "Budget Code: " + budgetCode);

        System.out.println("There are " + encounters.size() + " encounters in this site on this event.");
        for (Encounter enc: encounters){
            enc.printShortInfo();
        }
    }

}
