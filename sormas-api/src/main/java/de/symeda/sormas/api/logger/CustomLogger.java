package de.symeda.sormas.api.logger;

public interface CustomLogger {

    void logInfo(String tag, String msg);
    void logError(String tag, String msg);
    void logObj(String tag, Object obj);
}
