package validate_sa_id;

/**
 * Provides validation for South African ID numbers.
 * Now in GREEN phase of TDD with standardized output formatting.
 */
public class ValidateSaId {

    /**
     * Validates a South African ID number with structured output.
     * 
     * @param idNumber The ID number to validate
     * @return boolean True if valid 13-digit format, false otherwise
     * 
     * @implNote
     * - Uses consistent output format for pass/fail cases
     * - Future enhancements will add Luhn algorithm validation
     */
    public static boolean validate(String idNumber) {
        String logPrefix = "[VALIDATOR]";
        String status;
        String reason;
        
        if (idNumber == null) {
            status = "FAIL";
            reason = "ID cannot be null";
        } else if (!idNumber.matches("\\d{13}")) {
            status = "FAIL";
            reason = "ID must be 13 digits";
        } else {
            status = "PASS";
            reason = "Valid 13-digit format";
        }
        
        // Standardized output format
        System.out.printf("%s STATUS: %-4s | ID: %-13s | REASON: %s%n",
                         logPrefix, status, idNumber, reason);
        
        return status.equals("PASS");
    }
}