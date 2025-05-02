package validate_sa_id;

public class ValidateSaId {
    /**
     * Validates a South African ID number
     * 1. Exactly 13 digits
     * 2. Valid date of birth (YYMMDD)
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
        
        return isValidDate(year, month, day);
    }
    
    private static boolean isValidDate(int year, int month, int day) {
        // Validate month (01-12)
        if (month < 1 || month > 12) {
            return false;
        }
        
        // Validate day
        if (day < 1) {
            return false;
        }
        
        // Get max days for month
        int maxDays;
        switch (month) {
            case 2: // February
                maxDays = isLeapYear(year) ? 29 : 28;
                break;
            case 4: case 6: case 9: case 11: // 30-day months
                maxDays = 30;
                break;
            default: // 31-day months
                maxDays = 31;
                break;
        }
        
        return day <= maxDays;
    }
    
    private static boolean isLeapYear(int year) {
        // Since we only have 2 digits, we need to handle century years carefully
        // Years 00-99  represent 1900-1999 or 2000-2099
        // 2000 was a leap year, 1900 was not
        
        // Lets  assume:
        // - If year % 4 == 0, it's a leap year
        // This works for all years except century years
        // For a more accurate solution, we will need to know the century
        
        // Another approach: treat 00 as 2000 (leap year) and other 00-99 as 1900-1999
        if (year == 0) {
            return true; // 2000 was a leap year
        }
        return year % 4 == 0;
    }
}

