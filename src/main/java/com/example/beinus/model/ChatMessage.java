package com.example.beinus.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class ChatMessage {
    private String nickname;
    private String content;
    private Date timestamp;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
