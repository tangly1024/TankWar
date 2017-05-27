package com.tlyong1992.client.constant;

/**
 * USER：tangly
 * DATE：2017/4/24
 * TIME：14:04
 */
public enum Dir {

    L("左"),

    LU("左上"),

    U("上"),

    RU("右上"),

    R("右"),

    RD("右下"),

    D("下"),

    LD("左下"),

    STOP("停止");

    private String value;

    public String getValue() {
        return value;
    }

    Dir(String value) {
        this.value = value;
    }
}