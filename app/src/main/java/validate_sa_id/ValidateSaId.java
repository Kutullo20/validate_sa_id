package validate_sa_id;

/**
 * Provides validation for South African ID numbers.
 * Currently in RED phase of TDD (Test-Driven Development) where implementation
 * is deliberately incorrect to fail tests initially.
 */
public class ValidateSaId {

    /**
     * Validates a South African ID number.
     * Currently in RED phase: always returns false to force test failures.
     * 
     * @param idNumber The ID number to validate (13-digit String)
     * @return boolean Always returns false in RED phase (will be implemented properly later)
     * 
     * @implNote
     * Current RED phase implementation:
     * - Logs validation attempt
     * - Deliberately fails all validations
     * - Actual validation logic will be added in GREEN phase
     */
    public static boolean validate(String idNumber) {
        // // Log validation attempt (RED phase)
        System.out.println("[VALIDATION] Checking ID: " + idNumber + " -> FAIL (RED PHASE)");
        
        // // RED PHASE: Intentional failure
        return false;
    }
}