package com.example.linebot.service;

import com.example.linebot.replier.RemindOn;
import com.example.linebot.value.ReminderItem;
import com.example.linebot.value.ReminderSlot;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    public RemindOn doReplyOfNewItem(MessageEvent<TextMessageContent> event){
        String userId = event.getSource().getUserId();
        TextMessageContent tmc = event.getMessage();
        String text = tmc.getText();
        ReminderSlot slot = new ReminderSlot(text);// スロットの抽出
        ReminderItem item = new ReminderItem(userId, slot);// データベース登録用

        return new RemindOn(text);
    }

}
