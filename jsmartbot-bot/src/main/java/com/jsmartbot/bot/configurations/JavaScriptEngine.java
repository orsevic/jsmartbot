package com.jsmartbot.bot.configurations;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergeyorlov
 **/
public class JavaScriptEngine {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptEngine.class);
    private ScriptEngine engine;

    public JavaScriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");
    }

    public Map<String, Object> evalParams(String paramsSupplier) {
        try {
            Object result = engine.eval(paramsSupplier);
            if (result instanceof Map) {
                return (Map<String, Object>) result;
            } else {
                throw new RuntimeException(String.format("Result of java script is not map - %s", result));
            }
        } catch (ScriptException e) {
            logger.error("Error while evaluate java script - {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
