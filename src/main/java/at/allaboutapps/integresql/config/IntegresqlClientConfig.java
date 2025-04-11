package at.allaboutapps.integresql.config;

import at.allaboutapps.integresql.util.EnvUtil;

public class IntegresqlClientConfig {

    private final String baseUrl;
    private final String apiVersion;

    // Private constructor for builder pattern or factory method
    private IntegresqlClientConfig(String baseUrl, String apiVersion) {
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
    }

    // --- Getters ---
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    // --- Factory Method ---

    /**
     * Creates a default configuration by reading environment variables.
     * Reads "INTEGRESQL_CLIENT_BASE_URL" (default: "<a href="http://integresql:5000/api">...</a>")
     * Reads "INTEGRESQL_CLIENT_API_VERSION" (default: "v1")
     *
     * @return A new IntegresqlClientConfig instance with default or environment-provided values.
     */
    public static IntegresqlClientConfig defaultConfigFromEnv() {
        String envBaseUrl = EnvUtil.getEnv("INTEGRESQL_CLIENT_BASE_URL", "http://integresql:5000/api");
        String envApiVersion = EnvUtil.getEnv("INTEGRESQL_CLIENT_API_VERSION", "v1");
        return new IntegresqlClientConfig(envBaseUrl, envApiVersion);
    }

    /**
     * Creates a configuration with specific values.
     *
     * @param baseUrl    The base URL of the IntegreSQL API (e.g., "<a href="http://localhost:5000/api">...</a>").
     * @param apiVersion The API version string (e.g., "v1").
     * @return A new IntegresqlClientConfig instance.
     */
    public static IntegresqlClientConfig customConfig(String baseUrl, String apiVersion) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Base URL cannot be null or empty");
        }
        if (apiVersion == null || apiVersion.trim().isEmpty()) {
            throw new IllegalArgumentException("API version cannot be null or empty");
        }
        return new IntegresqlClientConfig(baseUrl, apiVersion);
    }
}