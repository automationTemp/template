package config;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private static String propFileName = "config.properties";

    public static Map<String, String> settings = new HashMap<>();

    public static String getProperty(String property) {
        if (!settings.containsKey(property)) {
            System.out.println("Параметр " + property + " не найден в файле!");
        }
        return settings.get(property);
    }

    public static void setString(String property, String value) {
        settings.put(property, value);
    }

    public static boolean setStringsByPrefix(String prefix) {
        boolean found = false;
        Map<String, String> instance = new HashMap<>();
        for (String key : settings.keySet()) {
            if (key.startsWith(prefix + ".")) {
                found = true;
                instance.put(key.replaceFirst(prefix + ".", ""), getProperty(key));
            }
        }
        settings.putAll(instance);
        return found;
    }

    public static String getPropFileName() {
        return propFileName;
    }

    public static void setPropFileName(String propFileName) {
        Config.propFileName = propFileName;
    }
}
