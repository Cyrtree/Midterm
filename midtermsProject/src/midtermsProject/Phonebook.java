package midtermsProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Phonebook {
    private HashMap<String, Contact> contactByEmailMap;
    private HashMap<String, Contact> contactByUsernameMap; 
    private ArrayList<Contact> allContactsList;

    public Phonebook() {
        contactByEmailMap = new HashMap<>();
        contactByUsernameMap = new HashMap<>(); 
        allContactsList = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        if (contactByEmailMap.containsKey(contact.getEmail()) || 
            contactByUsernameMap.containsKey(contact.getUsername())) {
            return false;
        }
        contactByEmailMap.put(contact.getEmail(), contact);
        contactByUsernameMap.put(contact.getUsername(), contact); 
        allContactsList.add(contact);
        return true;
    }

    public Contact findContactByEmail(String email) {
        return contactByEmailMap.get(email);
    }

    public Contact findContactByUsername(String username) { 
        return contactByUsernameMap.get(username);
    }

    public List<Contact> getContactsSortedByLastName() {
        ArrayList<Contact> sortedList = new ArrayList<>(allContactsList);
        sortedList.sort(Comparator.comparing(Contact::getLastName));
        return sortedList;
    }

    public List<Contact> findContactsByCity(String city) {
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact contact : allContactsList) {
            if (contact.getCity().equalsIgnoreCase(city)) {
                results.add(contact);
            }
        }
        return results;
    }

    public List<Contact> findContactsByCompany(String company) {
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact contact : allContactsList) {
            if (contact.getCompany().equalsIgnoreCase(company)) {
                results.add(contact);
            }
        }
        return results;
    }
}