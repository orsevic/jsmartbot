package com.jsmartbot.bot.configurations;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListeners {

    @Autowired
    private JavaScriptEngine javaScriptEngine;
    @Autowired
    private ApplicationContext context;

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent() {
        System.out.println("a context refreshed event happened");
        bindBeans();
    }

    private void bindBeans() {
        String[] beanNames = BeanFactoryUtils.beanNamesIncludingAncestors(context);
        for (String bean : beanNames) {
            javaScriptEngine.bindGlobalVariable(bean, context.getBean(bean));
        }
    }
}