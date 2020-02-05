package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream fileInputStream = null;

    public static void configOrganizer(String[] commandLineArgs) throws IOException {
        parseCommandLineArgsToConfig(commandLineArgs);
        getPropValues();
        setPropertiesToConfig(System.getProperties());
        if (!checkExistAndSetAlias()) {
            throw new AssertionError("Не удалось считать данные для выбранного сервера!");
        }
    }

    //для смены конфигурационного файла
    private static void parseCommandLineArgsToConfig(String[] commandLineArgs) {
        for (String cmdLine : commandLineArgs) {
            if (cmdLine.startsWith("-Dproperties.file")) {
                Config.setPropFileName(cmdLine.replaceFirst(".*=\\s?", ""));
            }
        }
    }

    //получение параметров в виде <Наименовение> - <Значение>из текстового конфигурационного файла
    private static void getPropValues() throws IOException {
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(Config.getPropFileName());
            properties.load(fileInputStream);
            Enumeration enumeration = properties.keys();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement().toString();
                Config.setString(key, properties.getProperty(key));
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

    public static void setPropertiesToConfig(Map<?, ?> parameters) {
        for (Object propName : parameters.keySet()) {
            Object value = parameters.get(propName);
            Config.setString(String.valueOf(propName), String.valueOf(value));
        }
    }

    private static boolean checkExistAndSetAlias() {
        String aliasPrefix = Config.getProperty("current.server.alias");
        if (aliasPrefix == null || aliasPrefix.isEmpty()) {
            System.err.println("Параметр current.server.alias не найден, проверьте конфиг!");
        } else {
            Config.setStringsByPrefix(aliasPrefix);
            return true;
        }
        return false;
    }
}
