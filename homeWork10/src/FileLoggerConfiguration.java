import java.io.File;
import java.io.IOException;

public class FileLoggerConfiguration {
    private int size;
    private LoggingLevel logging;
    private String format;
    private String path;

    public FileLoggerConfiguration(int size, LoggingLevel logging, String format, String path) {
        this.size = size;
        this.logging = logging;
        this.format = format;
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LoggingLevel getLogging() {
        return logging;
    }

    public void setLogging(LoggingLevel logging) {
        this.logging = logging;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
