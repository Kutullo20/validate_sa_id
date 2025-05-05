package validate_sa_id;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ValidateSaId {

    public static IDAnalysis analyzeId(String idNumber) {
        List<String> errors = new ArrayList<>();
        IDDetails details = new IDDetails();

        // Basic validation
        if (idNumber == null || idNumber.length() != 13) {
            errors.add("ID must be exactly 13 digits");
            return new IDAnalysis(false, errors, details);
        }

        // Check all digits
        for (char c : idNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                errors.add("ID must contain only numbers");
                return new IDAnalysis(false, errors, details);
            }
        }

        // Extract components
        int year = Integer.parseInt(idNumber.substring(0, 2));
        int month = Integer.parseInt(idNumber.substring(2, 4));
        int day = Integer.parseInt(idNumber.substring(4, 6));
        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        int citizenship = Integer.parseInt(idNumber.substring(10, 11));

        // Validate components
        boolean validDate = validateDate(year, month, day, errors, details);
        boolean validGender = validateGender(genderDigits, errors, details);
        boolean validCitizen = validateCitizenship(citizenship, errors, details);
        boolean validLuhn = validateLuhn(idNumber, errors);

        if (validDate) {
            details.setDateOfBirth(formatDate(idNumber));
            details.setAge(calculateAge(idNumber));
        }

        return new IDAnalysis(
            errors.isEmpty(), 
            errors, 
            errors.isEmpty() ? details : null
        );
    }

    private static boolean validateDate(int year, int month, int day, 
                                     List<String> errors, IDDetails details) {
        if (month < 1 || month > 12) {
            errors.add("Invalid month (must be 01-12)");
            return false;
        }

        int maxDays = switch (month) {
            case 2 -> isLeapYear(year) ? 29 : 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };

        if (day < 1 || day > maxDays) {
            errors.add("Invalid day for month (max " + maxDays + " days)");
            return false;
        }
        return true;
    }

    private static boolean isLeapYear(int year) {
        int fullYear = year < 20 ? 2000 + year : 1900 + year;
        return (fullYear % 4 == 0 && (fullYear % 100 != 0 || fullYear % 400 == 0));
    }

    private static boolean validateGender(int genderDigits, 
                                       List<String> errors, IDDetails details) {
        if (genderDigits < 0 || genderDigits > 9999) {
            errors.add("Invalid gender digits (0000-9999)");
            return false;
        }
        details.setGender(genderDigits < 5000 ? "Female" : "Male");
        return true;
    }

    private static boolean validateCitizenship(int citizenship, 
                                            List<String> errors, IDDetails details) {
        if (citizenship != 0 && citizenship != 1) {
            errors.add("Invalid citizenship digit (must be 0 or 1)");
            return false;
        }
        details.setCitizenship(citizenship == 0 ? "SA Citizen" : "Permanent Resident");
        return true;
    }

    private static boolean validateLuhn(String id, List<String> errors) {
        int sum = 0;
        boolean alternate = false;
        for (int i = id.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(id.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit = (digit % 10) + 1;
            }
            sum += digit;
            alternate = !alternate;
        }
        if (sum % 10 != 0) {
            errors.add("Failed Luhn checksum validation");
            return false;
        }
        return true;
    }

    private static String formatDate(String id) {
        int year = Integer.parseInt(id.substring(0, 2));
        int month = Integer.parseInt(id.substring(2, 4));
        int day = Integer.parseInt(id.substring(4, 6));
        int fullYear = year < 20 ? 2000 + year : 1900 + year;
        return String.format("%02d/%02d/%04d", day, month, fullYear);
    }

    private static int calculateAge(String id) {
        int year = Integer.parseInt(id.substring(0, 2));
        int month = Integer.parseInt(id.substring(2, 4));
        int day = Integer.parseInt(id.substring(4, 6));
        int fullYear = year < 20 ? 2000 + year : 1900 + year;
        LocalDate dob = LocalDate.of(fullYear, month, day);
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public static class IDAnalysis {
        private final boolean isValid;
        private final List<String> errors;
        private final IDDetails details;

        public IDAnalysis(boolean isValid, List<String> errors, IDDetails details) {
            this.isValid = isValid;
            this.errors = errors;
            this.details = details;
        }

        // Getters
        public boolean isValid() { return isValid; }
        public List<String> getErrors() { return errors; }
        public IDDetails getDetails() { return details; }
    }

    public static class IDDetails {
        private String dateOfBirth;
        private int age;
        private String gender;
        private String citizenship;

        // Getters and Setters
        public String getDateOfBirth() { return dateOfBirth; }
        public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
        public String getCitizenship() { return citizenship; }
        public void setCitizenship(String citizenship) { this.citizenship = citizenship; }
    }
}