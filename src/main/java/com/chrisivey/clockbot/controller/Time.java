package com.chrisivey.clockbot.controller;

import com.chrisivey.clockbot.entity.GtaEms;
import com.chrisivey.clockbot.entity.GtaPolice;
import com.chrisivey.clockbot.repository.GtaEmsRepo;
import com.chrisivey.clockbot.repository.GtaPoliceRepo;
import net.dv8tion.jda.api.entities.Message;
import org.springframework.stereotype.Component;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
                gtaEms = new GtaEms(message.getAuthor().getId(), date, date, 0, 0, 0, 1);
                gtaEmsRepo.save(gtaEms);
            }else if(character.getClockIn().getTime() > character.getClockOut().getTime() ){
                message.getChannel().sendMessage("You're already clocked in, you need to clock out.").complete();
            }else{
                gtaEms = gtaEmsRepo.findByDiscordUid(message.getAuthor().getId());
                gtaEms.setClockedBoolean(1);
                gtaEms.setClockIn(date);
                gtaEmsRepo.save(gtaEms);
            }
        }else if(job.equals("Police")){
            gtaPoliceRepo.findByDiscordUid(message.getAuthor().getId());
            GtaPolice gtaPolice =  new GtaPolice(message.getAuthor().getId(), null, null, 0, 0);
            gtaPoliceRepo.save(gtaPolice);
        }
        message.getChannel().sendMessage("yay " + job + " has clocked in!").complete();
    }

    public void alertClockOut(String job, Message message){
        GtaEms character = gtaEmsRepo.findByDiscordUid(message.getAuthor().getId());
        GtaEms gtaEms;
        Date oldDate = new Date(character.getClockOut().getTime());
        Date newDate = new Date();

        if(character.getClockedBoolean() == 1) {
            if (job.equals("EMS")) {

                long diffInMills = Math.abs(newDate.getTime() - oldDate.getTime());
                long diffInSeconds = TimeUnit.SECONDS.convert(diffInMills, TimeUnit.MILLISECONDS) % 60;
                long diffInMinutes = TimeUnit.MINUTES.convert(diffInMills, TimeUnit.MILLISECONDS) % 60;
                long diffInHours = TimeUnit.HOURS.convert(diffInMills, TimeUnit.MILLISECONDS) % 60;


                int obtainExtraHours = 0;
                int obtainLeftMinutes;
                if (character.getMinutes() + (int) diffInMinutes > 60) {
                    obtainExtraHours++;
                    obtainLeftMinutes = (character.getMinutes() + (int) diffInMinutes) % 60;
                } else {
                    obtainLeftMinutes = (int) diffInMinutes;
                }

                int calculateTotalHours = character.getWeeklyHours() + obtainExtraHours + (int) diffInHours;
                int calculateTotalMinutes = obtainLeftMinutes;

                gtaEms = gtaEmsRepo.findByDiscordUid(message.getAuthor().getId());

                gtaEms.setMinutes(calculateTotalMinutes);
                gtaEms.setWeeklyHours(calculateTotalHours);
                gtaEms.setTotalHours(calculateTotalHours);
                gtaEms.setClockOut(newDate);
                gtaEms.setClockedBoolean(0);
                gtaEmsRepo.save(gtaEms);

                message.getChannel().sendMessage("You played " + (int) diffInHours +
                        " hours, and " + obtainLeftMinutes + " minutes and " + diffInSeconds + " seconds.").complete();

            }
        } else {
            message.getChannel().sendMessage("You're not clocked in!").complete();
        }
    }
}
