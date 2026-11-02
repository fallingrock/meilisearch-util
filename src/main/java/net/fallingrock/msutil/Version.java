package net.fallingrock.msutil;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meilisearch.sdk.Client;
import lombok.Getter;

/**
 * Concrete implementation of json returned by {@link Client#getVersion()} method.
 */
@Getter
public class Version {

	/**
	 * Meilisearch server commit sha fingerprint
	 */
	@JsonProperty("commitSha")
	private String commitSha;

	/**
	 * Meilisearch server version
	 */
	@JsonProperty("pkgVersion")
	private String pkgVersion;

	/**
	 * Meilisearch server commit date
	 */
	@JsonProperty("commitDate")
	private String commitDate;
}
