package midtermsProject;

public class Validator {
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && !email.trim().isEmpty();
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d+") && !phone.trim().isEmpty();
    }
}