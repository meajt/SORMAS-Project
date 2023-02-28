package de.symeda.sormas.api.logger;

public class CustomLoggerFactory {
    private static CustomWebLogger customWebLogger;

    public static CustomLogger getLogger(LoggerType loggerType)
    {
        if (loggerType == LoggerType.WEB)
        {
            if (customWebLogger == null)
                customWebLogger = new CustomWebLogger();
            return customWebLogger;
        }
        throw new RuntimeException("Logger not found");
    }
}
