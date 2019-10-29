package com.chrisivey.clockbot;

import com.chrisivey.clockbot.controller.Time;
import com.chrisivey.clockbot.repository.GtaEmsRepo;
import com.chrisivey.clockbot.repository.GtaPoliceRepo;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Chat extends ListenerAdapter {
    private Time time;
    private GtaEmsRepo gtaEmsRepo;
    private GtaPoliceRepo gtaPoliceRepo;

    @Autowired
    public Chat(GtaEmsRepo gtaEmsRepo, GtaPoliceRepo gtaPoliceRepo){
        this.gtaEmsRepo = gtaEmsRepo;
        this.gtaPoliceRepo = gtaPoliceRepo;
        time = new Time(gtaEmsRepo, gtaPoliceRepo);
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String content = message.getContentDisplay();

        //For the police channels
        if (content.equals("!clockin") && message.getChannel().getId().equals("614868481757937664")) {
            time.alertClockIn("police", message);
        } else if (content.equals("!clockout") && message.getChannel().getId().equals("614868481757937664")) {
            message.getChannel().sendMessage("Yay Police clockout we did it!").complete();
        } else if (content.equals("!weekly") && message.getChannel().getId().equals("614868481757937664")) {
            message.getChannel().sendMessage("Yay Police weekly we did it!").complete();
        } else if (content.equals("!all") && message.getChannel().getId().equals("614868481757937664")) {
            message.getChannel().sendMessage("Yay Police all we did it!").complete();
        }

        //For the mechanic channels
        if (content.equals("!clockin") && message.getChannel().getId().equals("600524154080591872")) {
            time.alertClockIn("mechanic", message);
        } else if (content.equals("!clockout") && message.getChannel().getId().equals("600524154080591872")) {
            message.getChannel().sendMessage("Yay mechanic clockout we did it!").complete();
        } else if (content.equals("!weekly") && message.getChannel().getId().equals("600524154080591872")) {
            message.getChannel().sendMessage("Yay mechanic weekly we did it!").complete();
        } else if (content.equals("!all") && message.getChannel().getId().equals("600524154080591872")) {
            message.getChannel().sendMessage("Yay mechanic all we did it!").complete();
        }

        //For the EMS channels
        if (content.equals("!clockin") && message.getChannel().getId().equals("635536282184843265")) {
            time.alertClockIn("EMS", message);
        } else if (content.equals("!clockout") && message.getChannel().getId().equals("635536282184843265")) {
            message.getChannel().sendMessage("Yay EMS clockout we did it!").complete();
        } else if (content.equals("!weekly") && message.getChannel().getId().equals("635536282184843265")) {
            message.getChannel().sendMessage("Yay EMS weekly we did it!").complete();
        } else if (content.equals("!all") && message.getChannel().getId().equals("635536282184843265")) {
            message.getChannel().sendMessage("Yay EMS all we did it!").complete();
        }
    }
}
