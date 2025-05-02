package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void testComprehensiveValidId() {
        // This is a valid ID that passes all checks including Luhn
        String validId = "8001015009087";
        
        System.out.println("Testing comprehensive valid ID: " + validId);
        System.out.println("=======================================");
        
        // Validate all components
        assertTrue(ValidateSaId.validate(validId), "ID should be valid");
        assertEquals("male", ValidateSaId.getGender(validId), "Gender should be male");
        assertEquals("citizen", ValidateSaId.getCitizenship(validId), "Should be citizen");
        
        System.out.println("All validation checks passed for valid ID");
    }
}