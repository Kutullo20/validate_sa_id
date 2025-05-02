package validate_sa_id;

public class ValidateSaId {
    /**
     * Checks if string is exactly 13 characters long
     * @param idNumber Input to validate
     * @return true only if length is exactly 13 chars, false otherwise (including null)
     */
    public static boolean validate(String idNumber) {
        return idNumber != null && idNumber.length() == 13;
    }
}