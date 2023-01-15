package com.example.linebot.replier;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;

import java.net.URI;
import java.util.Random;

public class Omikuji implements Replier{

    @Override
    public Message reply(){
        int ranNum = new Random().nextInt(7);
        System.out.println(ranNum);
        String uriString = "";
        switch (ranNum){
            case 6:
                uriString = "https://3.bp.blogspot.com/-vQSPQf-ytsc/T3K7QM3qaQI/AAAAAAAAE-s/6SB2q7ltxwg/s180-c/omikuji_daikichi.png";
                break;
            case 5:
                uriString = "https://2.bp.blogspot.com/-27IG0CNV-ZE/VKYfn_1-ycI/AAAAAAAAqXw/fr6Y72lOP9s/s180-c/omikuji_kichi.png";
                break;
            case 4:
                uriString = "https://3.bp.blogspot.com/-_z-n-7gO3KA/T3K7MU3MdGI/AAAAAAAAE-k/8qs-jxqS4LE/s180-c/omikuji_chuukichi.png";
                break;
            case 3:
                uriString = "https://3.bp.blogspot.com/-nZt5pjGWT9E/T3K7TJ4wEZI/AAAAAAAAE_E/c1X2-N54EYo/s180-c/omikuji_syoukichi.png";
                break;
            case 2:
                uriString = "https://3.bp.blogspot.com/-JLNa8mwZRnU/T3K7StR-bEI/AAAAAAAAE-8/rQrDomz5MSw/s180-c/omikuji_suekichi.png";
                break;
            case 1:
                uriString = "https://4.bp.blogspot.com/-qCfF4H7YOvE/T3K7R5ZjQVI/AAAAAAAAE-4/Hd1u2tzMG3Q/s180-c/omikuji_kyou.png";
                break;
            default:
                uriString = "https://2.bp.blogspot.com/-h61ngruj0tE/T3K7RDUWmPI/AAAAAAAAE-0/KXtPY8fDwco/s180-c/omikuji_daikyou.png";
        }
        URI uri = URI.create(uriString);
        return new ImageMessage(uri,uri);
    }

}
