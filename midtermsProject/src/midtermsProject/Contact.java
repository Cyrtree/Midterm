package midtermsProject;

import java.util.LinkedList;

public class Contact {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String company;
    private LinkedList<String> interactionLog;

    public Contact(String username, String email, String firstName, String lastName, String phoneNumber, String city, String company) {
    	
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.company = company;
        this.interactionLog = new LinkedList<>();
    }
    
    public String getEmail() { 
    	return email; 
    	}
    public String getUsername() { 
    	return username; 
    	}
    public String getFirstName() { 
    	return firstName; 
    	}
    public String getLastName() { 
    	return lastName; 
    	}
    public String getPhoneNumber() { 
    	return phoneNumber; 
    	}
    public String getCity() { 
    	return city; 
    	}
    public String getCompany() { 
    	return company; 
    	}
    
    public void addInteraction(String interaction) {
        interactionLog.add(interaction);
        if (interactionLog.size() > 5) {
            interactionLog.removeFirst(); 
        }
    }

    public LinkedList<String> getInteractionLog() {
        return interactionLog;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s | Username: %s | Email: %s | Phone: %s | City: %s | Company: %s", firstName, lastName, username, email, phoneNumber, city, company);
    }
}