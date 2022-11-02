import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileLoggerConfiguration config = new FileLoggerConfiguration(15, LoggingLevel.DEBUG, "[%t][tf][%m]", "debug.txt");
        FileLogger fileLogger = new FileLogger(config);
        fileLogger.createFile();
    }
}
