package me.park.GabiaMailParser;

import me.park.GabiaMailParser.util.MailReloadUtil;
import me.park.GabiaMailParser.view.LoginView;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GabiaMailParserMain {

    public static Image img = Toolkit.getDefaultToolkit().getImage(GabiaMailParserMain.class.getResource("/icon.png"));

    public static void main(String[] args) {
        new LoginView();

        //스케쥴러
        MailReloadUtil mailReloadUtil = new MailReloadUtil();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(mailReloadUtil.runnable, 0, AppConstants.RELOAD_TIME, TimeUnit.MINUTES);

    }
}
