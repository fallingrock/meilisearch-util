package net.fallingrock.msutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Client;
import lombok.NonNull;

/**
 * General utilities that can be useful for Meilisearch
 */
public final class GeneralUtils {

    private GeneralUtils() {
        // nothing
    }

    /**
     * Parse version json into Version object.
     *
     * @param json json formatted results of {@link Client#getVersion()} method.
     *
     * @return version object
     */
    public static Version parseVersion(@NonNull String json) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.readValue(json, Version.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
