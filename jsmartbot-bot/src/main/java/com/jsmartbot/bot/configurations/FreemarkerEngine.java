package com.jsmartbot.bot.configurations;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author sergeyorlov
 **/
public class FreemarkerEngine {

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
    {
        cfg.setClassForTemplateLoading(this.getClass(), "/");
    }

    public FreemarkerEngine() {
    }

    public String process(String templateStr, Map<String, Object> params) {

        try {
            Template temp = new Template("name", new StringReader(templateStr), cfg);

            StringWriter out = new StringWriter(1024);
            temp.process(params, out);

            return out.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
