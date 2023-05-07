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

public class EventSchedule {

    ArrayList<Event> scheduledEvents;

    public EventSchedule() {
        scheduledEvents = new ArrayList<Event>();
    }

    public Event newEvent(Site s, String budgetnumer, String date) {
        Event newevent = new Event(s, budgetnumer, date);
        scheduledEvents.add(newevent);
        return newevent;
    }

    // finds all positive confirmations for all events that match the budgetnumber
    public int getEventConfirmedCasesByBudgetNumber(String budgetnumber) {
        int sum = 0;
        for (Event e : scheduledEvents) {

            if (e.isMatch(budgetnumber))

                sum = sum + e.getConfirmedTotals();

        }
        return sum;
    }

    public ArrayList<Event> getScheduledEvents() {
        return scheduledEvents;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the Events Schedule.");
        System.out.println("There are " + scheduledEvents.size() + " events.");
        for (Event ev: scheduledEvents){
            ev.printShortInfo();
        }
        
        System.out.println("-----------------------------------------------");
        System.out.println("                                              ");
    }

}