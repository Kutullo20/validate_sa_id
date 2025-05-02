package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void testInvalidLengthStringsAllShouldFail() {
        String[] invalidIds = {
            null,     // null
            "",       // empty
            "1",      // 1 char
            "123",    // 3 chars
            "123456789012", // 12 chars (1 short)
        };

        System.out.println("INVALID LENGTH VALIDATION (ALL SHOULD FAIL)");
        System.out.println("=========================================");
        

        for (String id : invalidIds) {  
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-15s → %s%n",
                id == null ? "null" : "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            
            assertFalse(result, 
                String.format("Input: %s unexpectedly passed validation", 
                    id == null ? "null" : "\"" + id + "\""));
        }
    }
    
    @Test
    public void testValidLengthStringsAllShouldPass() {
        String[] validIds = {
            "1234567890123",      
            "0000000000000",     
            "9999999999999",     
            "8001015009087",      
            "9002015009087"       
        };

        System.out.println("\nVALID LENGTH VALIDATION (ALL SHOULD PASS)");
        System.out.println("=======================================");
        
        for (String id : validIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (CORRECT)" : "FAIL (UNEXPECTED)"
            );
            
            assertTrue(result, 
                String.format("Input: %s unexpectedly failed validation", "\"" + id + "\""));
        }
    }

    @Test
    public void testInvalidLengthStringsTooLongShouldFail() {
        String[] invalidIds = {
            "12345678901234",    // 14 chars (1 too long)
            "123456789012345",    // 15 chars
            "12345678901234567890", // 20 chars
            "00000000000000000000000000000000" // 32 chars
        };
    
        System.out.println("\nTOO LONG VALIDATION (ALL SHOULD FAIL)");
        System.out.println("====================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            
            assertFalse(result, 
                String.format("Input: %s unexpectedly passed validation", "\"" + id + "\""));
        }
    }
}