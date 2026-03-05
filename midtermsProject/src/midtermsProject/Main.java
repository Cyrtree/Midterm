package midtermsProject;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Phonebook phonebook = new Phonebook();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DataGenerator.preLoadData(phonebook);
        
        while (true) {
            System.out.println("\n=== SMART PHONEBOOK ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Find Contact by Email");
            System.out.println("3. Find Contact by Username"); 
            System.out.println("4. Log Interaction");
            System.out.println("5. Show All Contacts (Sorted)");
            System.out.println("6. Search by City");
            System.out.println("7. Search by Company");
            System.out.println("8. Find Same City/Company");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1: addContact(); break;
                case 2: findContactByEmail(); break;
                case 3: findContactByUsername(); break;
                case 4: logInteraction(); break;
                case 5: showAllContacts(); break;
                case 6: searchByCity(); break;
                case 7: searchByCompany(); break;
                case 8: nestedLoopSearch(); break;
                case 0: 
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default: System.out.println("Invalid choice");
            }
        }
    }

    private static void addContact() {
        System.out.println("\n--- ADD CONTACT ---");
        String username = getInput("Username: ");
        String email = getEmail("Email: ");
        String firstName = getInput("First Name: ");
        String lastName = getInput("Last Name: ");
        String phone = getPhone("Phone: ");
        String city = getInput("City: ");
        String company = getInput("Company: ");
        
        Contact contact = new Contact(username, email, firstName, lastName, 
                                      phone, city, company);
        
        if (phonebook.addContact(contact)) {
            System.out.println("Contact added!");
        } else {
            System.out.println("Email or Username already exists!");
        }
    }

    private static void findContactByEmail() {
        System.out.println("\n--- FIND BY EMAIL ---");
        String email = getEmail("Email: ");
        Contact contact = phonebook.findContactByEmail(email);
        displayContact(contact);
    }

    private static void findContactByUsername() {
        System.out.println("\n--- FIND BY USERNAME ---");
        String username = getInput("Username: ");
        Contact contact = phonebook.findContactByUsername(username);
        displayContact(contact);
    }

    private static void displayContact(Contact contact) {
        if (contact != null) {
            System.out.println("\nContact: " + contact);
            System.out.println("Full Name: " + contact.getFirstName() + " " + contact.getLastName());
            System.out.println("Phone: " + contact.getPhoneNumber());
            System.out.println("City: " + contact.getCity());
            System.out.println("Company: " + contact.getCompany());
            System.out.println("\nLast 5 Interactions:");
            if (contact.getInteractionLog().isEmpty()) {
                System.out.println("- No interactions");
            } else {
                for (String log : contact.getInteractionLog()) {
                    System.out.println("- " + log);
                }
            }
        } else {
            System.out.println("Contact not found");
        }
    }

    private static void logInteraction() {
        System.out.println("\n--- LOG INTERACTION ---");
        String email = getEmail("Email: ");
        Contact contact = phonebook.findContactByEmail(email);
        
        if (contact != null) {
            String interaction = getInput("Interaction: ");
            contact.addInteraction(interaction);
            System.out.println("Interaction logged!");
        } else {
            System.out.println("Contact not found");
        }
    }

    private static void showAllContacts() {
        System.out.println("\n--- ALL CONTACTS (Sorted by Last Name) ---");
        List<Contact> contacts = phonebook.getContactsSortedByLastName();
        if (contacts.isEmpty()) {
            System.out.println("No contacts");
        } else {
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    private static void searchByCity() {
        System.out.println("\n--- SEARCH BY CITY ---");
        String city = getInput("City: ");
        List<Contact> results = phonebook.findContactsByCity(city);
        
        if (results.isEmpty()) {
            System.out.println("No contacts in " + city);
        } else {
            System.out.println("Contacts in " + city + ":");
            for (Contact c : results) {
                System.out.println("- " + c.getFirstName() + " " + c.getLastName() + 
                                 " (" + c.getUsername() + ")");
            }
        }
    }

    private static void searchByCompany() {
        System.out.println("\n--- SEARCH BY COMPANY ---");
        String company = getInput("Company: ");
        List<Contact> results = phonebook.findContactsByCompany(company);
        
        if (results.isEmpty()) {
            System.out.println("No contacts in " + company);
        } else {
            System.out.println("Contacts in " + company + ":");
            for (Contact c : results) {
                System.out.println("- " + c.getFirstName() + " " + c.getLastName() + 
                                 " (" + c.getUsername() + ")");
            }
        }
    }

    private static void nestedLoopSearch() {
        System.out.println("\n--- SAME CITY/COMPANY PAIRS ---");
        List<Contact> contacts = phonebook.getContactsSortedByLastName();
        boolean found = false;
        
        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                Contact c1 = contacts.get(i);
                Contact c2 = contacts.get(j);
                
                if (c1.getCity().equalsIgnoreCase(c2.getCity())) {
                    System.out.println(c1.getFirstName() + " and " + 
                                     c2.getFirstName() + " both live in " + c1.getCity());
                    found = true;
                }
                if (c1.getCompany().equalsIgnoreCase(c2.getCompany())) {
                    System.out.println(c1.getFirstName() + " and " + 
                                     c2.getFirstName() + " both work at " + c1.getCompany());
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No matching pairs found");
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Cannot be empty");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static String getEmail(String prompt) {
        System.out.print(prompt);
        String email = scanner.nextLine().trim();
        while (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email (must contain @)");
            System.out.print(prompt);
            email = scanner.nextLine().trim();
        }
        return email;
    }

    private static String getPhone(String prompt) {
        System.out.print(prompt);
        String phone = scanner.nextLine().trim();
        while (!Validator.isValidPhone(phone)) {
            System.out.println("Invalid phone (digits only)");
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
        }
        return phone;
    }
}