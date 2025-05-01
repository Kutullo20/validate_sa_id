package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for South African ID validation with standardized output.
 */
public class ValidateSaIdTest {
    
    /**
     * Tests valid ID numbers with consistent reporting format.
     */
    @Test
    public void testValidIds() {
        System.out.println("\n=== TEST VALID IDs ===");
        
        String[] validIds = {"2001014800086", "2909035800085"};
        
        for (String id : validIds) {
            System.out.printf("[TESTER] Testing ID: %s%n", id);
            boolean result = ValidateSaId.validate(id);
            
            // Standardized result format
            String testResult = result ? "PASS" : "FAIL";
            System.out.printf("[TESTER] RESULT: %-4s | ID: %-13s%n", 
                             testResult, id);
            
            assertTrue(result, "Validation failed for ID: " + id);
        }
        
        System.out.println("=== TEST COMPLETE ===\n");
    }
}