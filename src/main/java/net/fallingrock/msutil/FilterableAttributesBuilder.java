package net.fallingrock.msutil;

import com.meilisearch.sdk.model.FilterableAttributesConfig;
import com.meilisearch.sdk.model.FilterableAttributesFeatures;
import com.meilisearch.sdk.model.FilterableAttributesFilter;

/**
 * A builder class for creating and configuring instances of {@code FilterableAttributesConfig}.
 * This class provides a fluent API to specify various filtering and feature options for
 * filterable attributes.
 */
@SuppressWarnings("unused")
public class FilterableAttributesBuilder {

    private final FilterableAttributesConfig attributesConfig;
    private final FilterableAttributesFilter attributesFilter;
    private final FilterableAttributesFeatures attributesFeatures;

    /**
     * Constructs a new instance of {@code FilterableAttributesBuilder} and initializes its internal configuration
     * for filtering and managing attribute features.
     *
     * @param field the name of the field to be used in the filterable attributes configuration
     */
    public FilterableAttributesBuilder(String field) {
        attributesFilter = new FilterableAttributesFilter();

        attributesFeatures = new FilterableAttributesFeatures();
        attributesFeatures.setFilter(attributesFilter);

        attributesConfig = FilterableAttributesConfig.simple(field);
        attributesConfig.setFeatures(attributesFeatures);
    }

    /**
     * Builds and returns the configured {@code FilterableAttributesConfig} instance.
     *
     * @return the {@code FilterableAttributesConfig} instance that has been configured using the builder.
     */
    public FilterableAttributesConfig build() {
        return attributesConfig;
    }

    /**
     * Configures the filter to enable or disable equality checking for attributes.
     *
     * @param equality a boolean flag indicating whether equality checking should be enabled (true) or disabled (false)
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withEquality(boolean equality) {
        attributesFilter.setEquality(equality);
        return this;
    }

    /**
     * Configures the filter to enable or disable comparison logic for attributes.
     *
     * @param comparison a boolean flag indicating whether comparison logic should be enabled (true) or disabled (false)
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withComparison(boolean comparison) {
        attributesFilter.setComparison(comparison);
        return this;
    }

    /**
     * Configures whether facet searching should be enabled or disabled for attributes.
     *
     * @param facetSearch a boolean flag indicating whether facet searching should be enabled (true) or disabled (false)
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withFacetSearch(boolean facetSearch) {
        attributesFeatures.setFacetSearch(facetSearch);

        return this;
    }
}

