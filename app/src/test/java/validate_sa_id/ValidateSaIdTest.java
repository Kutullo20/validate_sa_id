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
}
