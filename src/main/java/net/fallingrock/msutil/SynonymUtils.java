package net.fallingrock.msutil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utility class for generating synonyms mappings.
 * This class provides methods to create a map where each string in a collection is mapped
 * to an array of its synonyms, which include all other strings in the collection except itself.
 * <p>
 * The class is designed to be non-instantiable and provides static utility methods for synonym generation.
 */
@SuppressWarnings("unused")
public final class SynonymUtils {
    private SynonymUtils() {
        // private constructor to prevent instantiation
    }

    /**
     * Generates a map of synonyms for each string in the provided array, where the key is a string
     * from the input array and the value is an array of all other strings from the array that are not the key.
     *
     * @param names a varargs array of strings for which synonyms need to be generated
     * @return a map where each string from the input array is a key, and the value is an array of strings
     * that are all other items from the array except the key
     */
    public static Map<String, String[]> synonymGenerator(String... names) {
        return synonymGenerator(Arrays.asList(names));
    }

    /**
     * Generates a map of synonyms for each string in the provided list, where the key is a string
     * from the input list and the value is an array of all other strings from the list that are not the key.
     *
     * @param names a list of strings for which synonyms need to be generated
     * @return a map, ordered by first occurrence in {@code names}, where each string from the input
     * list is a key, and the value is an array of strings that are all other items from the
     * list except the key
     * @throws IllegalArgumentException if {@code names} contains duplicate entries
     */
    public static Map<String, String[]> synonymGenerator(List<String> names) {
        return names.stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            name -> names.stream()
                                         .filter(other -> !Objects.equals(name, other))
                                         .toArray(String[]::new),
                            (a, b) -> {
                                throw new IllegalArgumentException("Duplicate name: " + Arrays.toString(a));
                            },
                            LinkedHashMap::new
                    ));
    }
}
