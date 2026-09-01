# MeiliSearch Utilities

This is a collection of utilities for working with MeiliSearch.

## Classes

### FilterableAttributesBuilder

FilterableAttributesBuilder provides a convient way to build FilterableAttributesConfig using a fluent interface.

For example:

```java
FilterableAttributesConfig filterableAttributes = new FilterableAttributesBuilder("fieldname")
        .withComparison(false)
        .withEquality(true)
        .withFacetSearch(true)
        .build();
```

### SynonymUtils

The SynonymUtils.synonymGenerator method is used to generate synonyms for a given set of strings.

It returns a map containing every possible combination of synonyms for each input string.

For instance, if you pass an array or list of strings like `["A", "B", "C"]`, you will get a map like:

```json
{
  "A": [
    "B",
    "C"
  ],
  "B": [
    "A",
    "C"
  ],
  "C": [
    "A",
    "B"
  ]
}
```

