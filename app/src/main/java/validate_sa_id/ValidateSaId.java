package validate_sa_id;

public class ValidateSaId {
    /**
     * Only checks if string is too short (<13 chars)
     * @param idNumber Input to validate
     * @return false if length <13 (or null), no true cases yet
     */
    public static boolean validate(String idNumber) {
        // Only fail cases - no pass logic implemented yet
        return !(idNumber == null || idNumber.length() < 13);
    }
}
