package validate_sa_id;

public class ValidateSaId {
    /**
     * Only checks if string is too short (<13 chars)
     * @param idNumber Input to validate
     * @return false if length <13 (or null), no true cases yet
     */
    public static boolean validate(String idNumber) {
        // Both fail cases and  pass logic implemented in the test class
        // Return true UNLESS the input is null OR shorter than 13 chars
        return !(idNumber == null || idNumber.length() < 13);
    }
}
