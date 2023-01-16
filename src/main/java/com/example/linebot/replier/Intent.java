package com.example.linebot.replier;

import java.util.EnumSet;
import java.util.regex.Pattern;

public enum Intent {

    // メッセージの正規表現パターンに対応するやりとり状態の定義
    REMINDER("^(\\d{1,2}):(\\d{1,2})に(.{1,32})$"),UNKNOWN(".+");

    private final String regexp;

    private Intent(String regexp){
        this.regexp = regexp;
    }

    // メッセージからやりとり状態を判断
    public static Intent whichIntent(String text){
        // 全てのIntent(REMINDER, UNKNOWN)を取得
        EnumSet<Intent> set = EnumSet.allOf(Intent.class);
        // 引数textが、REMINDER, UNKNOWNのパターンに当てはまるかチェックする
        // 当てはまった方を戻り値とする
        for (Intent intent : set){
            if(Pattern.matches(intent.regexp, text)){
                return intent;
            }
        }
        return UNKNOWN;
    }

    public String getRegexp(){
        return regexp;
    }

}
