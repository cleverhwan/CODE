package kr.co.code.common.logging;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class CodeLogger {
    private final String name;
    private Logger log;
    private static ThreadLocal logThreadLocal = new ThreadLocal();

    private String callerFQCN = CodeLogger.class.getName() + ".";

    protected CodeLogger() {
        log = Logger.getRootLogger();
        this.name = log.getName();
    }

    protected CodeLogger(String name) {
        log = Logger.getLogger(name);
        this.name = log.getName();
    }

    public String getName() {
        return name;
    }

    public boolean isTraceEnabled() {
        if(!log.isEnabledFor(Level.TRACE))
            return false;
        else return Level.TRACE.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void trace(Object message) {
        log.log(callerFQCN,Level.TRACE, message, null);
    }

    public void trace(Object message, Throwable t) {
        log.log(callerFQCN,Level.TRACE, message, t);
    }

    public boolean isDebugEnabled() {
        Level p = Level.DEBUG;
        if(!log.isEnabledFor(p))
            return false;
        else
            return p.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void debugValue(Object obj) {
        Field[] fd = obj.getClass().getDeclaredFields();

        log.log(callerFQCN,Level.DEBUG, obj.getClass().getName() + " =========================================>",null);
        for(int i=0; i<fd.length; i++){
            try
            {
                String getter= "get" + fd[i].getName();
                Method method = obj.getClass().getMethod(getter, new Class[0]);
                log.log(callerFQCN,Level.DEBUG, fd[i].getName() + " : " + method.invoke(obj,new Class[0]),null);
            }
            catch ( Exception e)
            {
                new Exception(e);
            }
        }
    }

    public void startDebug(String s) {
        log.log(callerFQCN,Level.DEBUG, "/****************************"+ s + " Block START ****************************/",null);
    }

    public void endDebug(String s) {
        log.log(callerFQCN,Level.DEBUG, "/****************************"+ s + " Block END ****************************/",null);

    }
    public void debugValue(String s, Object obj) {
        log.log(callerFQCN,Level.DEBUG, s + " ===================================> " + obj.toString(),null);
    }

    public void debug(long longval) {
        log.log(callerFQCN,Level.DEBUG, new Long(longval),null);
    }

    public void debug(String arg, long longval) {
        log.log(callerFQCN,Level.DEBUG,  arg + " " + new Long(longval),null);
    }

    public void debug(Object message) {
        log.log(callerFQCN,Level.DEBUG, message,null);
    }

    public void debug(Object message, Throwable t) {
        log.log(callerFQCN,Level.DEBUG, message, t);
    }


    public boolean isInfoEnabled() {
        Level p = Level.INFO;
        if(!log.isEnabledFor(p))
            return false;
        else
            return p.isGreaterOrEqual(log.getEffectiveLevel());
    }

    public void info(Object message) {
        log.log(callerFQCN,Level.INFO, message, null);
    }

    public void info(Object message, Throwable t) {
        log.log(callerFQCN,Level.INFO, message, t);
    }
/*
    public void warn(Object message) {
        log.log(Level.WARN, message);
    }

    public void warn(Object message, Throwable t) {
        log.log(Level.WARN, message, t);
    }
    */

    public void error(Object message) {
        log.log(callerFQCN,Level.ERROR, message, null);
    }

    public void error(Object message, Throwable t) {
        log.log(callerFQCN,Level.ERROR, message, t);
    }

    public void fatal(Object message) {
        log.log(callerFQCN,Level.FATAL, message, null);
    }

    public void fatal(Object message, Throwable t) {
        log.log(callerFQCN,Level.FATAL, message, t);
    }

    public void fatal(Object message, Throwable t, String sendCd) {
        log.log(callerFQCN,Level.FATAL, message, t);
    }

    public void log(Level p, Object message) {
        log.log(p, message);
    }

    public void log(Level p, Object message, Throwable t) {
        log.log(p, message, t);
    }

    public void log(Object message) {
        log.log(Level.OFF, message);
    }


    public static CodeLogger setLogger(String name) {
    	CodeLogger logger = new CodeLogger(name);
        logThreadLocal.set(logger);
        return logger;
    }

    public static CodeLogger getLogger() {
    	CodeLogger logger = (CodeLogger) logThreadLocal.get();
        if ( logger == null ) logger = new CodeLogger();
        return logger;
    }

    public static CodeLogger getLogger(String name) {
    	CodeLogger logger = new CodeLogger(name);
        return logger;
    }

    public static CodeLogger getLogger(Class clazz) {
    	CodeLogger logger = new CodeLogger(clazz.getName());
        return logger;
    }

}
