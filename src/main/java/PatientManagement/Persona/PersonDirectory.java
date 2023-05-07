package PatientManagement.Persona;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import PatientManagement.Clinic.Location;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class PersonDirectory {
    ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<Person>();
    }

    public Person newPerson(String id, int age, Location city) {
        Person p = new Person(id, age, city);
        personList.add(p);
        return p;
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public Person findPerson(String id) {

        for (Person p : personList) {
            if (p.isMatch(id)) {
                return p;
            }
        }
        return null; // not found after going through the whole list
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void printShortInfo(){
        System.out.println("Checking what's inside the person directory.");
        System.out.println("There are " + personList.size() + " patients.");
        for (Person p: personList){
            p.printShortInfo();
        }
        
        System.out.println("-----------------------------------------------");
        System.out.println("                                              ");
    }
}
