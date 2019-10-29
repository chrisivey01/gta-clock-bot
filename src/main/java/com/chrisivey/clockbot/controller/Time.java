package com.chrisivey.clockbot.controller;

import com.chrisivey.clockbot.entity.GtaEms;
import com.chrisivey.clockbot.entity.GtaPolice;
import com.chrisivey.clockbot.repository.GtaEmsRepo;
import com.chrisivey.clockbot.repository.GtaPoliceRepo;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Transactional
public class Time {
    private GtaEmsRepo gtaEmsRepo;
    private GtaPoliceRepo gtaPoliceRepo;

    public Time(GtaEmsRepo gtaEmsRepo, GtaPoliceRepo gtaPoliceRepo) {
        this.gtaEmsRepo = gtaEmsRepo;
        this.gtaPoliceRepo = gtaPoliceRepo;
    }

    public void alertClockIn(String job, Message message){
        Date date = new Date();

        if(job.equals("EMS")) {
            GtaEms character = gtaEmsRepo.findByDiscordUid(message.getAuthor().getId());
            GtaEms gtaEms;
            if(character == null) {
                gtaEms = new GtaEms(message.getAuthor().getId(), date, date, 0, 0);
                gtaEmsRepo.save(gtaEms);
            }else{
                gtaEms = this.gtaEmsRepo.findByDiscordUid(message.getAuthor().getId());

                System.out.println(gtaEms);

            }

        }else if(job.equals("Police")){
            gtaPoliceRepo.findByDiscordUid(message.getAuthor().getId());
            GtaPolice gtaPolice =  new GtaPolice(message.getAuthor().getId(), null, null, 0, 0);
            gtaPoliceRepo.save(gtaPolice);
        }

        message.getChannel().sendMessage("yay " + job + " has clocked in!").complete();
    }


}
