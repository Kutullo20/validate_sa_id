package validate_sa_id;

public class ValidateSaId {
    /**
     * Validates a South African ID number
     * 1. Exactly 13 digits
     * 2. Valid date of birth (YYMMDD)
     * 3. Valid gender digits (SSSS) - 0000-4999 female, 5000-9999 male
     * @param idNumber ID number to validate
     * @return true if valid, false otherwise
     */
    public static boolean validate(String idNumber) {
        // Basic validation
        if (idNumber == null || idNumber.length() != 13) {
            return false;
        }
        
        // Check all digits
        for (char c : idNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        
        // Extract date components
        int year = Integer.parseInt(idNumber.substring(0, 2));
        int month = Integer.parseInt(idNumber.substring(2, 4));
        int day = Integer.parseInt(idNumber.substring(4, 6));
        
        // Extract gender digits 
        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        
        return isValidDate(year, month, day) && isValidGender(genderDigits);
    }
    
    private static boolean isValidDate(int year, int month, int day) {
      
        if (month < 1 || month > 12) {
            return false;
        }
        
        if (day < 1) {
            return false;
        }
        
        int maxDays;
        switch (month) {
            case 2: maxDays = isLeapYear(year) ? 29 : 28; break;
            case 4: case 6: case 9: case 11: maxDays = 30; break;
            default: maxDays = 31; break;
        }
        
        return day <= maxDays;
    }
    
    private static boolean isLeapYear(int year) {
        if (year == 0) {
            return true;
        }
        return year % 4 == 0;
    }
    
    private static boolean isValidGender(int genderDigits) {
        return true;
    }
    
    /**
     * Determines gender from ID number
     * @param idNumber South African ID number
     * @return "female" if SSSS is 0000-4999, "male" if 5000-9999
     * @throws IllegalArgumentException if ID number is invalid
     */
    public static String getGender(String idNumber) {
        if (!validate(idNumber)) {
            throw new IllegalArgumentException("Invalid South African ID number");
        }
        
        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        return genderDigits < 5000 ? "female" : "male";
    }
}
