import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {
    private final FileLoggerConfiguration loggerConfiguration;
    static int sizeFile;

    public FileLogger(FileLoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
    }

    public void createFile() {
        try {
            File file = new File(loggerConfiguration.getPath());
            if(!file.exists()) {
                file.createNewFile();
            }
            sizeFile = (int) file.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debug(String str) {
            if(sizeFile < loggerConfiguration.getSize()) {
                try {
                    log(str, LoggingLevel.DEBUG);
                    throw new FileMaxSizeReachedException();
                } catch (FileMaxSizeReachedException e) {
                    System.out.println(loggerConfiguration.getSize() + " " + loggerConfiguration.getPath().length() + " " + loggerConfiguration.getPath());
                }
            }
    }

    public void info(String str) {
            if(str.length() < loggerConfiguration.getSize()) {
                try {
                    log(str, LoggingLevel.INFO);
                    throw new FileMaxSizeReachedException();
                } catch (FileMaxSizeReachedException e) {
                    System.out.println(loggerConfiguration.getSize() + " " + loggerConfiguration.getPath().length() + " " + loggerConfiguration.getPath());
                }
            }
    }

    public void log(String str, LoggingLevel level) {
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(loggerConfiguration.getPath()));
            String format = loggerConfiguration.getFormat().replace("%t", "16.09").replace("%f", level.name()).replace("%m", str);
            buf.write(format);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
