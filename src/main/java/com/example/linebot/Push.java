package com.example.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

import com.linecorp.bot.model.response.BotApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;

// このクラスをWebブラウザからのアクセスを引き受けるControllerにするための、Springフレームワークのアノテーション
@RestController
public class Push {

    private static final Logger log =  LoggerFactory.getLogger(Push.class);

    // push先のユーザーID
    private String userID = "Ua1ab0c49181f38bdc623124f10402b21";

    private final LineMessagingClient messagingClient;

    @Autowired
    public Push(LineMessagingClient lineMessagingClient){
        this.messagingClient = lineMessagingClient;
    }

    // テスト
    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    @GetMapping("timetone")
    @Scheduled(cron = "0 */1 * * * *", zone = "Asia/Tokyo")
    public String pushTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a K:mm");
        String text = dtf.format(LocalDateTime.now());
        try{
            PushMessage pMsg = new PushMessage(userID, new TextMessage(text));
            BotApiResponse resp = messagingClient.pushMessage(pMsg).get();
            log.info("Sent messages: {}",resp);
        }
        catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
        return text;
    }

}
