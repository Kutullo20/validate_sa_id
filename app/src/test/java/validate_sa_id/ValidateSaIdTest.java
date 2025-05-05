package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void testAnyValidId() {
        // Use any valid ID to test
        String anyValidId = ""; 
        
        ValidateSaId.IDAnalysis result = ValidateSaId.analyzeId(anyValidId);
        
        // Basic validation checks
        assertTrue(result.isValid());
        assertNotNull(result.getDetails());
        
        // Extract gender digits to determine expected gender
        int genderDigits = Integer.parseInt(anyValidId.substring(6, 10));
        String expectedGender = genderDigits < 5000 ? "Female" : "Male";
        
        // Dynamic assertion based on the ID's actual gender digits
        assertEquals(expectedGender, result.getDetails().getGender());
        
        System.out.println("VALID ID ANALYSIS:");
        System.out.println("ID: " + anyValidId);
        System.out.println("Date of Birth: " + result.getDetails().getDateOfBirth());
        System.out.println("Age: " + result.getDetails().getAge() + " years");
        System.out.println("Gender: " + result.getDetails().getGender());
        System.out.println("Citizenship: " + result.getDetails().getCitizenship());
    }

    @Test
    public void testAnyInvalidId() {
         // Use any Invalid ID to test
        String anyInvalidId = ""; 
        
        ValidateSaId.IDAnalysis result = ValidateSaId.analyzeId(anyInvalidId);
        
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
        
        System.out.println("INVALID ID ANALYSIS:");
        System.out.println("ID: " + anyInvalidId);
        result.getErrors().forEach(System.out::println);
    }
}