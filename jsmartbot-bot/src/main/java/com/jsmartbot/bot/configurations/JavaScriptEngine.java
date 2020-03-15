package com.jsmartbot.bot.configurations;

import java.util.Map;
import java.util.Optional;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author sergeyorlov
 **/
public class JavaScriptEngine {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptEngine.class);
    private final ScriptEngine engine;
    private final ScriptEngineManager manager;
//    private final Bindings bindings;

    public JavaScriptEngine() {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("nashorn");
//        bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
    }

    public Object evalJs(String paramsSupplier, Map<String, Object> contextParams) {
        try {
            Bindings contextBindings = engine.createBindings();
            contextBindings.putAll(contextParams);

            return engine.eval(paramsSupplier, contextBindings);
        } catch (ScriptException e) {
            logger.error("Error while evaluate java script - {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void bindGlobalVariable(String name, Object value) {
//        bindings.put(name, value);
        manager.put(name, value);
    }

}
