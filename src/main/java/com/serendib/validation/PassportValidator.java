package main.java.com.serendib.validation;

public class PassportValidator implements ValidationService {
    @Override
    public boolean validate(String passport) {
        return passport.matches("[A-Z]{1}[0-9]{7}");
    }
}