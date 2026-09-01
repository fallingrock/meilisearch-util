package net.fallingrock.msutil;

import com.meilisearch.sdk.model.FilterableAttributesConfig;
import com.meilisearch.sdk.model.FilterableAttributesFeatures;
import com.meilisearch.sdk.model.FilterableAttributesFilter;

/**
 * Fluent builder for {@link FilterableAttributesConfig}.
 */
@SuppressWarnings("unused")
public class FilterableAttributesBuilder {

    private final String[] fields;
    private boolean equality;
    private boolean comparison;
    private boolean facetSearch;

    public FilterableAttributesBuilder(String[] fields) {
        this.fields = fields;
    }

    public FilterableAttributesBuilder(String field) {
        this(new String[]{field});
    }

    public FilterableAttributesBuilder withEquality(boolean equality) {
        this.equality = equality;
        return this;
    }

    public FilterableAttributesBuilder withComparison(boolean comparison) {
        this.comparison = comparison;
        return this;
    }

    public FilterableAttributesBuilder withFacetSearch(boolean facetSearch) {
        this.facetSearch = facetSearch;
        return this;
    }

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
