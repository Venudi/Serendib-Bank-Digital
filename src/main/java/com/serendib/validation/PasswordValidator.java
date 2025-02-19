package main.java.com.serendib.validation;

public class PasswordValidator implements ValidationService {
    @Override
    public boolean validate(String password) {
        // At least 8 characters and at most 15 characters
        // At least one number, special character, uppercase letter and lowercase letter
        // None of the following special characters ' ( ) * , . / : \ _ ~ - or spaces
        return password.length() >= 8 && password.length() <= 15 && password.matches(".*[A-Z].*") && password.matches(".*[a-z].*") && password.matches(".*[0-9].*") &&
        password.matches(".*[!#$@%^&+=].*") && !password.matches(".*[ '()*,./:\\_~-].*");
    }
}