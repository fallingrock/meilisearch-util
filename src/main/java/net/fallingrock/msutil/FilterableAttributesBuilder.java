package net.fallingrock.msutil;

import com.meilisearch.sdk.model.FilterableAttributesConfig;
import com.meilisearch.sdk.model.FilterableAttributesFeatures;
import com.meilisearch.sdk.model.FilterableAttributesFilter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A builder class for constructing instances of {@link FilterableAttributesConfig}.
 * It allows configuring filterable attributes, such as equality, comparison, and facet search.
 */
@SuppressWarnings("unused")
public class FilterableAttributesBuilder {

    private final String[] fields;
    private boolean equality;
    private boolean comparison;
    private boolean facetSearch;

    /**
     * Initializes a new instance of the {@code FilterableAttributesBuilder} class with the specified fields.
     * This builder is used to configure filterable attributes for use in {@code FilterableAttributesConfig}.
     *
     * @param field1 single field to be representing the fields to be configured as filterable attributes
     * @param remainingFields additional fields to be configured as filterable attributes
     */
    public FilterableAttributesBuilder(String field1, String... remainingFields) {
        var allFields = new ArrayList<String>();
        allFields.add(field1);

        if (remainingFields.length > 0) {
            allFields.addAll(Arrays.asList(remainingFields));
        }

        this.fields = allFields.toArray(String[]::new);
    }

    /**
     * Sets the equality filter for the filterable attributes and returns the builder instance.
     *
     * @param equality a boolean indicating whether fields should support equality filtering
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withEquality(boolean equality) {
        this.equality = equality;
        return this;
    }

    /**
     * Sets the comparison filter for the filterable attributes and returns the builder instance.
     *
     * @param comparison a boolean indicating whether fields should support comparison filtering
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withComparison(boolean comparison) {
        this.comparison = comparison;
        return this;
    }

    /**
     * Sets whether the fields should support facet search and returns the builder instance.
     *
     * @param facetSearch a boolean indicating whether fields should support facet search
     * @return the current instance of {@code FilterableAttributesBuilder} for method chaining
     */
    public FilterableAttributesBuilder withFacetSearch(boolean facetSearch) {
        this.facetSearch = facetSearch;
        return this;
    }

    /**
     * Builds and returns a new instance of {@code FilterableAttributesConfig} based on the configuration
     * defined in the current {@code FilterableAttributesBuilder}.
     *
     * @return a configured instance of {@code FilterableAttributesConfig}, which contains the specified
     * fields and their associated features, such as filtering and facet search capabilities
     */
    public FilterableAttributesConfig build() {
        var filter = new FilterableAttributesFilter();
        filter.setEquality(equality);
        filter.setComparison(comparison);

        var features = new FilterableAttributesFeatures();
        features.setFilter(filter);
        features.setFacetSearch(facetSearch);

        return new FilterableAttributesConfig(fields, features);
    }
}
