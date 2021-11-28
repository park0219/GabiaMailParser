package me.park.GabiaMailParser;

import me.park.GabiaMailParser.util.MailReloadUtil;
import me.park.GabiaMailParser.view.LoginView;

import java.util.Date;
import java.util.Timer;

public class GabiaMailParserMain {

    public static void main(String[] args) {
        new LoginView();

        //스케쥴러
        Timer timer = new Timer();
        MailReloadUtil mailReloadUtil = new MailReloadUtil();
        timer.schedule(mailReloadUtil, new Date(System.currentTimeMillis() + AppConstants.RELOAD_TIME), AppConstants.RELOAD_TIME);

    }
}
