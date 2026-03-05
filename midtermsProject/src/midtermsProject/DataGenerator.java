package midtermsProject;

public class DataGenerator {
    public static void preLoadData(Phonebook phonebook) {
        phonebook.addContact(new Contact("Obit", "obet@email.com", "Obet", "Fernando", "1234567890", "New York", "ABC Corp"));
        phonebook.addContact(new Contact("Ekaj", "jake@email.com", "Jake", "Meneses", "9876543210", "Los Angeles", "XYZ Inc"));
        phonebook.addContact(new Contact("Stiven", "steven@email.com", "Steven", "Tolentino", "23125435", "Gotham", "Wayne Enterprises"));
        phonebook.addContact(new Contact("Jar", "rajj@email.com", "Rajj", "Silvestre", "123532213", "Metropolis", "Daily Planet"));

        // Pre-load some interactions for demo
        Contact c = phonebook.findContactByEmail("obet@email.com");
        if (c != null) {
            c.addInteraction("Called at 10:30 AM");
            c.addInteraction("Left voicemail");
        }
    }
}