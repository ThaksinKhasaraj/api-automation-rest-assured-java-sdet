package util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {

    private static Map<String, Object> config;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = ConfigLoader.class
                    .getClassLoader()
                    .getResourceAsStream("config/api-automated-user.json");

            config = mapper.readValue(is, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getApi() {
        return (Map<String, String>) config.get("api");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getLogin() {
        return (Map<String, String>) config.get("login");
    }
}
