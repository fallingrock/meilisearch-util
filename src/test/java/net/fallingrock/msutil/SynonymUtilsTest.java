package net.fallingrock.msutil;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SynonymUtilsTest {

    @Test
    void synonymGenerator() {
        // Test with an array of unique names
        Map<String, String[]> synonyms = SynonymUtils.synonymGenerator("apple", "banana", "cherry");

        assertEquals(3, synonyms.size(), "Map size should match the number of unique names.");
        assertArrayEquals(new String[]{"banana", "cherry"}, synonyms.get("apple"));
        assertArrayEquals(new String[]{"apple", "cherry"}, synonyms.get("banana"));
        assertArrayEquals(new String[]{"apple", "banana"}, synonyms.get("cherry"));
    }

    @Test
    void testSynonymGenerator() {
        // Test with duplicates in the list
        List<String> names = List.of("dog", "cat", "hampster");
        Map<String, String[]> synonyms = SynonymUtils.synonymGenerator(names);

        assertEquals(3, synonyms.size(), "Map size should match the number of unique names.");
        assertArrayEquals(new String[]{"cat", "hampster"}, synonyms.get("dog"));
        assertArrayEquals(new String[]{"dog", "hampster"}, synonyms.get("cat"));

        // Test with an empty list
        synonyms = SynonymUtils.synonymGenerator(List.of());
        assertTrue(synonyms.isEmpty(), "Map should be empty when input list has no names.");
    }
}

