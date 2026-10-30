package net.fallingrock.msutil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralUtilsTest {

    /**
     * Tests for the parseVersion method in GeneralUtils class.
     * This method parses a JSON string into a Version object.
     */

    @Test
    void testParseVersion_ValidJson() {
        // Arrange
        String validJson = "{ \"commitSha\": \"abc123\", \"pkgVersion\": \"1.0.0\", \"commitDate\": \"2026-10-30\" }";

        // Act
        Version version = GeneralUtils.parseVersion(validJson);

        // Assert
        assertNotNull(version);
        assertEquals("abc123", version.getCommitSha());
        assertEquals("1.0.0", version.getPkgVersion());
        assertEquals("2026-10-30", version.getCommitDate());
    }

    @Test
    void testParseVersion_InvalidJson() {
        // Arrange
        String invalidJson = "{ \"invalidField\": \"abc123\" }";

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> GeneralUtils.parseVersion(invalidJson));
        assertNotNull(exception.getMessage());
    }

    @Test
    void testParseVersion_EmptyJson() {
        // Arrange
        String emptyJson = "{}";

        // Act
        Version version = GeneralUtils.parseVersion(emptyJson);

        // Assert
        assertNotNull(version);
        assertNull(version.getCommitSha());
        assertNull(version.getPkgVersion());
        assertNull(version.getCommitDate());
    }

    @Test
    void testParseVersion_MalformedJson() {
        // Arrange
        String malformedJson = "{ \"commitSha\": \"abc123\", \"pkgVersion\": \"1.0.0\", ";

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                                                  () -> GeneralUtils.parseVersion(malformedJson));
        assertNotNull(exception.getMessage());
    }
}
