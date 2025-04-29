package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for validating South African ID numbers.
 * Currently in RED phase of TDD (Test-Driven Development) where tests are designed to fail first.
 */
public class ValidateSaIdTest {
    
    /**
     * Tests validation of valid South African ID numbers.
     * In RED phase, this test expects validation to fail (negative testing).
     * Uses sample valid IDs that should pass in final implementation.
     */
    @Test
    public void testValidIds() {  
        // Test phase marker
        System.out.println("\n=== TEST START (RED PHASE) ===");
        
        // Sample valid South African ID numbers (should pass validation when implemented)
        String[] validIds = {"2001014800086", "2909035800085"};
        
        // Test each ID in the array
        for (String id : validIds) {
            System.out.println("Testing ID: " + id);
            
            // Validate the ID number
            boolean result = ValidateSaId.validate(id);  
            
            // Print formatted test result
            System.out.printf("RESULT: %s -> %s%n", id, result ? "PASS" : "FAIL");
            
            // Assertion will fail in RED phase (expected behavior)
            assertTrue(result, "RED PHASE: Should fail for ID " + id);
        }
        
        // End of test phase marker
        System.out.println("=== TEST END (EXPECTED FAILURE) ===\n");
    }
}