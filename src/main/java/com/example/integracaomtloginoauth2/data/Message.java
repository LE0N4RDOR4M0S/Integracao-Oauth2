package com.example.integracaomtloginoauth2.data;

public final class Message {

    private static MessageService messageService;

    private Message() {
    }

    public static void setMessageService(MessageService messageService) {
        Message.messageService = messageService;
    }

    public static String toLocale(String msgCode) {
        return messageService.toLocale(msgCode);
    }

}
