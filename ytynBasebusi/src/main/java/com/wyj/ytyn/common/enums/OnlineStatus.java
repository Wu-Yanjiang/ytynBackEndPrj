package com.wyj.ytyn.common.enums;

/**
 * 用户会话
 */
public enum OnlineStatus {

    on_line("在线"),
    off_line("离线");

    private final String info;

    OnlineStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
