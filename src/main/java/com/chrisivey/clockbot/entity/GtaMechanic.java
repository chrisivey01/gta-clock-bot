package com.chrisivey.clockbot.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gta_mechanic")
public class GtaMechanic {

    @Id
    @Column(name = "discord_uid")
    String discordUid;

    @Column(name = "clock_in")
    @Temporal(TemporalType.TIMESTAMP)
    Date clockIn = new Date();

    @Column(name = "clock_out")
    @Temporal(TemporalType.TIMESTAMP)
    Date clockOut = new Date();

    @Column(name = "total_hours")
    int totalHours;

    @Column(name = "weekly_hours")
    int weeklyHours;

    int minutes;

    @Column(name = "clocked_boolean")
    int clockedBoolean;

    public GtaMechanic(){}

    public GtaMechanic(String discordUid, Date clockIn, Date clockOut, int totalHours, int weeklyHours, int minutes, int clockedBoolean) {
        this.discordUid = discordUid;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.totalHours = totalHours;
        this.weeklyHours = weeklyHours;
        this.minutes = minutes;
        this.clockedBoolean = clockedBoolean;
    }

    public String getDiscordUid() {
        return discordUid;
    }

    public void setDiscordUid(String discordUid) {
        this.discordUid = discordUid;
    }

    public Date getClockIn() {
        return clockIn;
    }

    public void setClockIn(Date clockIn) {
        this.clockIn = clockIn;
    }

    public Date getClockOut() {
        return clockOut;
    }

    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getClockedBoolean() {
        return clockedBoolean;
    }

    public void setClockedBoolean(int clockedBoolean) {
        this.clockedBoolean = clockedBoolean;
    }
}
