package main.java.com.serendib.validation;

public class NicValidator implements ValidationService {
    @Override
    public boolean validate(String nic) {
        return nic.matches("\\d{9}[Vv]?|\\d{12}");
    }
}