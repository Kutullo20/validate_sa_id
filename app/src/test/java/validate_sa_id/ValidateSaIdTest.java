package validate_sa_id;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    // Basic Format Validation Tests

    @Test
    public void testInvalidLengthStringsAllShouldFail() {
        String[] invalidIds = {
            null,     // null
            "",       // empty
            "1",      // 1 char
            "123",    // 3 chars
            "123456789012", // 12 chars (1 short)
            "12345678901234" // 14 chars (1 long)
        };

        System.out.println("INVALID LENGTH VALIDATION (ALL SHOULD FAIL)");
        System.out.println("=========================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                id == null ? "null" : "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            assertFalse(result);
        }
    }
    
    @Test
    public void testValidLengthStringsAllShouldPass() {
        String[] validIds = {
           "0001015009087",  // 1 Jan 2000 - valid
            "9912315009087",  // 31 Dec 1999 - valid
            "0002295009087",  // 29 Feb 2000 - valid (leap year)
            "0402295009087"   // 29 Feb 2004 - valid (leap year)
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
            assertTrue(result);
        }
    }
    
    @Test
    public void testStringsWithSpecialCharactersShouldFail() {
        String[] invalidIds = {
            "123456789012A",    // contains letter
            "12345678901 3",    // contains space
            "12345678901-3",    // contains hyphen
            "12345678901_3",    // contains underscore
            "12345678901.3"     // contains period
        };

        System.out.println("\nSPECIAL CHARACTER VALIDATION (ALL SHOULD FAIL)");
        System.out.println("===========================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            assertFalse(result);
        }
    }

  
    // Year Validation Tests
   
    @Test
    public void testAllTwoDigitYearsShouldPass() {
        String[] validIds = {
            "0001015009087",  // year 00
            "9901015009087",  // year 99
            "5001015009087"   // year 50
        };

        System.out.println("\nVALID YEAR VALIDATION (ALL SHOULD PASS)");
        System.out.println("====================================");
        
        for (String id : validIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (CORRECT)" : "FAIL (UNEXPECTED)"
            );
            assertTrue(result);
        }
    }

  
    // Month Validation Tests
   
    @Test
    public void testValidMonthsShouldPass() {
        String[] validIds = {
            "0001015009087",  // January
            "0012015009087",  // December
            "0006015009087"   // June
        };

        System.out.println("\nVALID MONTH VALIDATION (ALL SHOULD PASS)");
        System.out.println("======================================");
        
        for (String id : validIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (CORRECT)" : "FAIL (UNEXPECTED)"
            );
            assertTrue(result);
        }
    }

    @Test
    public void testInvalidMonthsShouldFail() {
        String[] invalidIds = {
            "0000015009087",  // month 00
            "0013015009087",  // month 13
            "0099015009087"   // month 99
        };

        System.out.println("\nINVALID MONTH VALIDATION (ALL SHOULD FAIL)");
        System.out.println("=======================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            assertFalse(result);
        }
    }

   
    // Day Validation Tests
   
    @Test
    public void testValidDaysShouldPass() {
        String[] validIds = {
            "0001015009087",  // 1 Jan
            "0001315009087",  // 31 Jan
            "0004305009087",  // 30 Apr
            "0002285009087",  // 28 Feb (non-leap)
            "0002295009087"   // 29 Feb (leap year 2000)
        };

        System.out.println("\nVALID DAY VALIDATION (ALL SHOULD PASS)");
        System.out.println("====================================");
        
        for (String id : validIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (CORRECT)" : "FAIL (UNEXPECTED)"
            );
            assertTrue(result);
        }
    }

    @Test
    public void testInvalidDaysShouldFail() {
        String[] invalidIds = {
            "0001005009087",  // day 00
            "0001325009087",  // day 32 in Jan
            "0004315009087",  // day 31 in Apr
            "0002305009087",  // day 30 in Feb (non-leap)
            "0102295009087"   // day 29 in Feb 2001 (not leap year)
        };

        System.out.println("\nINVALID DAY VALIDATION (ALL SHOULD FAIL)");
        System.out.println("=====================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            assertFalse(result);
        }
    }

    // Leap Year Validation Tests


    @Test
    public void testValidLeapYearsShouldPass() {
        String[] validIds = {
            "0002295009087",  // 29 Feb 2000
            "0402295009087",  // 29 Feb 2004
            "1202295009087",  // 29 Feb 2012
            "9602295009087"   // 29 Feb 1996
        };

        System.out.println("\nVALID LEAP YEAR VALIDATION (ALL SHOULD PASS)");
        System.out.println("==========================================");
        
        for (String id : validIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (CORRECT)" : "FAIL (UNEXPECTED)"
            );
            assertTrue(result);
        }
    }

    @Test
    public void testInvalidLeapYearsShouldFail() {
        String[] invalidIds = {
            "0102295009087",  // 29 Feb 2001
            "0502295009087",  // 29 Feb 2005
            "1302295009087",  // 29 Feb 2013
            "9902295009087"   // 29 Feb 1999
        };

        System.out.println("\nINVALID LEAP YEAR VALIDATION (ALL SHOULD FAIL)");
        System.out.println("============================================");
        
        for (String id : invalidIds) {
            boolean result = ValidateSaId.validate(id);
            System.out.printf(
                "Input: %-20s → %s%n",
                "\"" + id + "\"",
                result ? "PASS (UNEXPECTED)" : "FAIL (CORRECT)"
            );
            assertFalse(result);
        }
    }

    @Test
    public void testValidFemaleIds() {
        String[] validFemaleIds = {
            "9202200000087",  // 0000 (female)
            "9202201234087",  // 1234 (female)
            "9202204999087"   // 4999 (female)
        };

        System.out.println("\nVALID FEMALE IDs (0000-4999)");
        System.out.println("=========================");
        
        for (String id : validFemaleIds) {
            boolean isValid = ValidateSaId.validate(id);
            System.out.printf(
                "ID: %s → %s%n", 
                id, 
                isValid ? "VALID (PASS)" : "INVALID (FAIL)"
            );
            assertTrue(isValid, "Should PASS: Valid female ID");
        }
    }

    // ===== VALID MALE IDs (5000-9999) =====
    @Test
    public void testValidMaleIds() {
        String[] validMaleIds = {
            "9202205000087",  // 5000 (male)
            "9202207500087",  // 7500 (male)
            "9202209999087"   // 9999 (male)
        };

        System.out.println("\nVALID MALE IDs (5000-9999)");
        System.out.println("=======================");
        
        for (String id : validMaleIds) {
            boolean isValid = ValidateSaId.validate(id);
            System.out.printf(
                "ID: %s → %s%n", 
                id, 
                isValid ? "VALID (PASS)" : "INVALID (FAIL)"
            );
            assertTrue(isValid, "Should PASS: Valid male ID");
        }
    }

    // ===== INVALID FEMALE IDs (5000-9999 but marked as female) =====
    @Test
    public void testInvalidFemaleIds() {
        String[] invalidFemaleIds = {
            "9202205000087",  // 5000 (should be male)
            "9202207500087",  // 7500 (should be male)
            "9202209999087"   // 9999 (should be male)
        };

        System.out.println("\nINVALID FEMALE IDs (INCORRECT GENDER DIGITS)");
        System.out.println("=====================================");
        
        for (String id : invalidFemaleIds) {
            boolean isValid = ValidateSaId.validate(id);
            System.out.printf(
                "ID: %s → %s%n", 
                id, 
                isValid ? "VALID (PASS)" : "INVALID (FAIL)"
            );
            assertTrue(isValid, "Should PASS (valid ID format, but wrong gender)");
        }
    }

    // ===== INVALID MALE IDs (0000-4999 but marked as male) =====
    @Test
    public void testInvalidMaleIds() {
        String[] invalidMaleIds = {
            "9202200000087",  // 0000 (should be female)
            "9202201234087",  // 1234 (should be female)
            "9202204999087"   // 4999 (should be female)
        };

        System.out.println("\nINVALID MALE IDs (INCORRECT GENDER DIGITS)");
        System.out.println("===================================");
        
        for (String id : invalidMaleIds) {
            boolean isValid = ValidateSaId.validate(id);
            System.out.printf(
                "ID: %s → %s%n", 
                id, 
                isValid ? "VALID (PASS)" : "INVALID (FAIL)"
            );
            assertTrue(isValid, "Should PASS (valid ID format, but wrong gender)");
        }
    }

    // ===== COMPLETELY INVALID IDs (Wrong length/format) =====
    @Test
    public void testCompletelyInvalidIds() {
        String[] invalidIds = {
            null,               // null
            "",                // empty
            "123",             // too short
            "920220500008A",   // contains letter
            "920220500008712"  // too long (14 digits)
        };

        System.out.println("\nCOMPLETELY INVALID IDs (SHOULD FAIL)");
        System.out.println("===============================");
        
        for (String id : invalidIds) {
            boolean isValid = ValidateSaId.validate(id);
            System.out.printf(
                "ID: %-15s → %s%n", 
                id == null ? "null" : "\"" + id + "\"",
                isValid ? "VALID (FAIL)" : "INVALID (PASS)"
            );
            assertFalse(isValid, "Should FAIL: Invalid ID structure");
        }
    }
}
