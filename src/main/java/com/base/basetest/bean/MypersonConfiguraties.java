package com.base.basetest.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Configuration  //如果在 DemoApplication.java 設定了 ConfigurationScan ，這裡就不用再寫引用這個
@ConfigurationProperties(prefix = "myperson")
public class MypersonConfiguraties {
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MypersonConfiguraties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;
}