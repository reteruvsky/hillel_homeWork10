import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FileLogger {
    private final FileLoggerConfiguration loggerConfiguration;
    static int sizeFile;

    public FileLogger(FileLoggerConfiguration loggerConfiguration) {
        this.loggerConfiguration = loggerConfiguration;
    }

    public void createFile() {
        try {
            File file = new File(loggerConfiguration.getPath());
            if (!file.exists()) {
                file.createNewFile();
            }
            sizeFile = (int) file.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debug(String str) {
        if (sizeFile < loggerConfiguration.getSize()) {
            try {
                log(str, LoggingLevel.DEBUG);
                throw new FileMaxSizeReachedException();
            } catch (FileMaxSizeReachedException e) {
                System.out.println(loggerConfiguration.getSize() + " " + loggerConfiguration.getPath().length() + " " + loggerConfiguration.getPath());
            }
        } else {
            createNewFile();
        }
    }

    public void info(String str) {
        if (sizeFile < loggerConfiguration.getSize()) {
            try {
                log(str, LoggingLevel.INFO);
                throw new FileMaxSizeReachedException();
            } catch (FileMaxSizeReachedException e) {
                System.out.println(loggerConfiguration.getSize() + " " + loggerConfiguration.getPath().length() + " " + loggerConfiguration.getPath());
            }
        } else {
            createNewFile();
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

    public static void createNewFile() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            File file = new File("Log_" + formatter.format(calendar.getTime()) + ".txt");
            if (file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
