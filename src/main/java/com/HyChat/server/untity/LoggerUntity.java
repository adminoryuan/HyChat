package com.HyChat.server.untity;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUntity {
    private static Logger log = Logger.getLogger(LinkageError.class.toString());
    static {
        log.setLevel(Level.INFO);
    }
    public static void LogInfo(String val){
        log.info(val);
    }
    public static void  LogWaring(String val){
        log.warning(val);
    }
    public static void LogServer(String val){
        log.severe(val);
    }
}
