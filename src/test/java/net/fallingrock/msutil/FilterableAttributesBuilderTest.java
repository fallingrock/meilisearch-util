package net.fallingrock.msutil;

import com.meilisearch.sdk.model.FilterableAttributesConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FilterableAttributesBuilderTest {

    @Test
    @DisplayName("Should build default configuration with a single field")
    void testSingleFieldDefaultConfiguration() {
        // Arrange
        FilterableAttributesBuilder builder = new FilterableAttributesBuilder("genre");

        // Act
        FilterableAttributesConfig config = builder.build();

        // Assert
        assertNotNull(config, "Config should not be null");
        assertArrayEquals(new String[]{"genre"}, config.getAttributePatterns());
        assertNotNull(config.getFeatures(), "Features should not be null");
        assertFalse(config.getFeatures().getFacetSearch(), "Default facetSearch should be false");
        assertNotNull(config.getFeatures().getFilter(), "Filter should not be null");
        assertFalse(config.getFeatures().getFilter().getEquality(), "Default equality should be false");
        assertFalse(config.getFeatures().getFilter().getComparison(), "Default comparison should be false");
    }

    @Test
    @DisplayName("Should build configuration with multiple fields provided via varargs")
    void testMultipleFieldsConfiguration() {
        // Arrange
        FilterableAttributesBuilder builder = new FilterableAttributesBuilder("genre", "author", "publishedYear");

        // Act
        FilterableAttributesConfig config = builder.build();

        // Assert
        assertNotNull(config);
        assertArrayEquals(new String[]{"genre", "author", "publishedYear"}, config.getAttributePatterns());
    }

    @Test
    @DisplayName("Should support fluent method chaining and return the same builder instance")
    void testFluentChainingReturnsSelf() {
        // Arrange
        FilterableAttributesBuilder builder = new FilterableAttributesBuilder("field");

        // Act & Assert
        assertSame(builder, builder.withEquality(true), "withEquality should return this");
        assertSame(builder, builder.withComparison(true), "withComparison should return this");
        assertSame(builder, builder.withFacetSearch(true), "withFacetSearch should return this");
    }

    @ParameterizedTest(name = "equality={0}, comparison={1}, facetSearch={2}")
    @CsvSource({
            "true, true, true",
            "true, false, false",
            "false, true, false",
            "false, false, true",
            "false, false, false",
            "true, true, false",
            "true, false, true",
            "false, true, true"
    })
    @DisplayName("Should correctly configure all feature flag combinations")
    void testFeatureFlagCombinations(boolean equality, boolean comparison, boolean facetSearch) {
        // Arrange & Act
        FilterableAttributesConfig config = new FilterableAttributesBuilder("category", "tag")
                .withEquality(equality)
                .withComparison(comparison)
                .withFacetSearch(facetSearch)
                .build();

        // Assert
        assertNotNull(config);
        assertArrayEquals(new String[]{"category", "tag"}, config.getAttributePatterns());
        assertNotNull(config.getFeatures());
        assertEquals(facetSearch, config.getFeatures().getFacetSearch());
        assertNotNull(config.getFeatures().getFilter());
        assertEquals(equality, config.getFeatures().getFilter().getEquality());
        assertEquals(comparison, config.getFeatures().getFilter().getComparison());
    }

    @Test
    @DisplayName("Should allow toggling flags multiple times, reflecting the latest state")
    void testOverwritingFlags() {
        // Arrange & Act
        FilterableAttributesConfig config = new FilterableAttributesBuilder("price")
                .withEquality(true)
                .withEquality(false)
                .withComparison(false)
                .withComparison(true)
                .withFacetSearch(true)
                .withFacetSearch(false)
                .build();

        // Assert
        assertNotNull(config);
        assertFalse(config.getFeatures().getFilter().getEquality());
        assertTrue(config.getFeatures().getFilter().getComparison());
        assertFalse(config.getFeatures().getFacetSearch());
    }

    @Test
    @DisplayName("Should allow multiple independent build invocations")
    void testMultipleBuildCalls() {
        // Arrange
        FilterableAttributesBuilder builder = new FilterableAttributesBuilder("title")
                .withEquality(true)
                .withComparison(true)
                .withFacetSearch(true);

        // Act
        FilterableAttributesConfig config1 = builder.build();
        FilterableAttributesConfig config2 = builder.build();

        // Assert
        assertNotNull(config1);
        assertNotNull(config2);
        assertNotSame(config1, config2);
        assertArrayEquals(config1.getAttributePatterns(), config2.getAttributePatterns());
        assertEquals(config1.getFeatures().getFacetSearch(), config2.getFeatures().getFacetSearch());
        assertEquals(config1.getFeatures().getFilter().getEquality(), config2.getFeatures().getFilter().getEquality());
        assertEquals(config1.getFeatures().getFilter().getComparison(), config2.getFeatures().getFilter().getComparison());
    }
}
