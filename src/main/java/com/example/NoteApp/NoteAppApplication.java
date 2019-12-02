package com.example.NoteApp;

import com.example.NoteApp.activemq.producter.ActivemqClient;
import com.example.NoteApp.configer.freemarkerconf;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;

@SpringBootApplication
@EnableJms
public class NoteAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {
        return new CommandLineRunner() {
            @Override
            @SuppressWarnings("unchecked")
            public void run(String... strings) throws Exception {
                //添加自定义解析器
                Map map = resolver.getAttributesMap();
                map.put("long2date", new freemarkerconf());
            }
        };
    }
}
