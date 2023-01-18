package com.example.linebot;

import com.example.linebot.replier.*;
import com.example.linebot.service.CovidGovService;
import com.example.linebot.service.ReminderService;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

@LineMessageHandler
public class Callback {

    private static final Logger log = LoggerFactory.getLogger(Callback.class);

    private final ReminderService reminderService;
    private final CovidGovService covidGovService;

    @Autowired
    public Callback(ReminderService reminderService, CovidGovService covidGovService){
        this.reminderService = reminderService;
        this.covidGovService = covidGovService;
    }

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
        Intent intent =  Intent.whichIntent(text);
        switch (intent){
            case REMINDER:
                RemindOn remindOn = reminderService.doReplyOfNewItem(event);
                return remindOn.reply();
            case COVID_TOTAL:
                CovidReport covidReport = covidGovService.doReplyWithCovid(event);
                return covidReport.reply();
            case UNKNOWN:
            default:
                Parrot parrot = new Parrot(event);
                return parrot.reply();
        }
//        switch (text){
//            case "やあ":
//                Greet greet = new Greet();
//                return greet.reply();
//            case "おみくじ":
//                Omikuji omikuji = new Omikuji();
//                return omikuji.reply();
//            default:
//                Parrot parrot = new Parrot(event);
//                return parrot.reply();
//        }
    }
}
