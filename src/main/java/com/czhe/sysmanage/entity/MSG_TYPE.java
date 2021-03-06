package com.czhe.sysmanage.entity;

public enum MSG_TYPE {

    TEXT(1, "文本"),
    IMAGE(2, "图片"),
    VIDEO(3, "视频");
    public final int code;
    public final String message;

    MSG_TYPE(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
