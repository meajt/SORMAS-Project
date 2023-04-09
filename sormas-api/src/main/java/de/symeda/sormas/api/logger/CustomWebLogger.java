package de.symeda.sormas.api.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWebLogger implements CustomLogger{
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(CustomWebLogger.class);
    @Override
    public void logInfo(String tag, String msg) {
        logger.info("\n"+tag+"::"+msg);
    }

    @Override
    public void logError(String tag, String msg) {
        logger.error("\n"+tag+"::"+msg);
    }

    @Override
    public void logObj(String tag, Object obj) {
        try {
            logger.info("\n"+tag+"::"+objectMapper.writeValueAsString(obj));
        }catch (Exception exe)
        {
            logger.error("Error while logging object");
            logger.error(tag, exe);
        }

    }
}
