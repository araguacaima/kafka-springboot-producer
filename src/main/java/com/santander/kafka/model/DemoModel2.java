package com.santander.kafka.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoModel2 {

    private String field1;
    private int field2;
    private DemoModel demo;

    public DemoModel2() {
    }

    public DemoModel2(String field1, int field2, DemoModel demo) {
        this.field1 = field1;
        this.field2 = field2;
        this.demo = demo;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public DemoModel getDemo() {
        return demo;
    }

    public void setDemo(DemoModel demo) {
        this.demo = demo;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
