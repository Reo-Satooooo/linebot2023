package com.example.linebot.value;

import com.example.linebot.replier.Intent;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReminderSlot {

    private LocalTime pushAt;
    private String pushText;

    public ReminderSlot(String text){
        // Slotに当たる部分を取り出す正規表現の仕組み（Matcher）を作る
        String regexp = Intent.REMINDER.getRegexp();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()){
            // textの中で、"hh:mmに〇〇"の正規表現に
            // マッチした1hh2mm3〇〇を取り出す
            int hours = Integer.parseInt(matcher.group(1));
            int minutes = Integer.parseInt(matcher.group(2));
            this.pushAt = LocalTime.of(hours, minutes);
            this.pushText = matcher.group(3);
        }
        else {
            //正規表現にマッチしない場合、実行事例外をthrowする
            throw new IllegalArgumentException("textをスロットに分けられません");
        }
    }

    public LocalTime getPushAt(){
        return pushAt;
    }

    public String getPushText(){
        return pushText;
    }
}
