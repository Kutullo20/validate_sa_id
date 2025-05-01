package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void testShortStringsAllShouldFail() {
        String[] invalidIds = {
            null,     // null
            "",       // empty
            "1",      // 1 char
            "123",    // 3 chars
            "123456789012" // 12 chars (1 short)
        };

        System.out.println("SHORT STRING VALIDATION (ALL SHOULD FAIL)");
        System.out.println("========================================");
        

        for (String id : invalidIds) {  
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-15s → %s%n",
                id == null ? "null" : "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            
            // Assert that the validation result is false, because all inputs in this test are expected to be invalid.
            assertFalse(result, 
                String.format("Input: %s unexpectedly passed validation", 
                    id == null ? "null" : "\"" + id + "\""));
        }
        
    }
}