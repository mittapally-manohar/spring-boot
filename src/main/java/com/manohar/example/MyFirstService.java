package com.manohar.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom-2.properties")
})
public class MyFirstService {

    @Value("${my.prop}")
    private String customPropertyFromAnotherFile;

    @Value("${my.prop.2}")
    private String customPropertyFromAnotherFile2;

    @Value("${my.property}")
    private String property;

    private MyFirstClass myFirstClass;

    public MyFirstService(@Qualifier("bean2") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "the dependency is saying : " + myFirstClass.sayHello();
    }

    public String getCustomPropertyFromAnotherFile() {
        return customPropertyFromAnotherFile;
    }

    public String getProperty() {
        return property;
    }

    public String getCustomPropertyFromAnotherFile2() {
        return customPropertyFromAnotherFile2;
    }
}
