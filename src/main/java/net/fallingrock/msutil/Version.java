package net.fallingrock.msutil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meilisearch.sdk.Client;
import lombok.Getter;

/**
 * Concrete implementation of json returned by {@link Client#getVersion()} method.
 */
@Getter
public class Version {

	@JsonProperty("commitSha")
	private String commitSha;

	@JsonProperty("pkgVersion")
	private String pkgVersion;

	@JsonProperty("commitDate")
	private String commitDate;
}
