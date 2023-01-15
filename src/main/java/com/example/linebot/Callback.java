package com.example.linebot;

import com.example.linebot.replier.Follow;
import com.example.linebot.replier.Parrot;
import com.example.linebot.replier.Greet;
import com.example.linebot.replier.Omikuji;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;

import com.linecorp.bot.model.message.Message;

import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event){
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public Message handleMessage(MessageEvent<TextMessageContent> event){
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        switch (text){
            case "やあ":
                Greet greet = new Greet();
                return greet.reply();
            case "おみくじ":
                Omikuji omikuji = new Omikuji();
                return omikuji.reply();
            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }
    }
}
