import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        FileLoggerConfiguration config = new FileLoggerConfiguration(15, LoggingLevel.DEBUG, "[%t][tf][%m]", "debug.txt");
        FileLogger fileLogger = new FileLogger(config);
        fileLogger.createFile();

        FileLoggerConfigurationLoader lg = new FileLoggerConfigurationLoader();

        FileLoggerConfigurationLoader.load("debug.txt");

    }
}
