package com.vulcanth.commons.api;

public class ChannelMessage {
    private String channel;
    private String subChannel;
    private String message;

    public ChannelMessage(String channel, String subChannel, String message) {
        this.channel = channel;
        this.subChannel = subChannel;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public String getMessage() {
        return message;
    }
}
