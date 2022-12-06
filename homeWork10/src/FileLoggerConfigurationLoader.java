import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class FileLoggerConfigurationLoader {

    public static FileLoggerConfiguration load(String pathFile) throws IOException {
        Properties property = new Properties();

        FileReader reader = new FileReader("logger.properties");
        property.load(reader);

        FileLoggerConfiguration fileConfig = new FileLoggerConfiguration(Integer.parseInt(property.getProperty("MAX-SIZE")),
                LoggingLevel.valueOf(property.getProperty("LEVEL")),
                property.getProperty("FORMAT"),
                property.getProperty("FILE"));

        return fileConfig;
    }
}
