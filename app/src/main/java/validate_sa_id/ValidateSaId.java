package validate_sa_id;

public class ValidateSaId {
    /**
     * Checks if string is exactly 13 characters long and contains only digits
     * @param idNumber Input to validate
     * @return true only if length is exactly 13 chars and contains only digits, false otherwise (including null)
     */
    public static boolean validate(String idNumber) {
        if (idNumber == null || idNumber.length() != 13) {
            return false;
        }
        
        // Check that all characters are digits
        for (char c : idNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
}
