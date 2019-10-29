package com.chrisivey.clockbot;


import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;

@Component
public class Bot {
    @Autowired
    public Bot(@Value("${token}")String token, Chat chat){
        try {
            new JDABuilder(token)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .addEventListeners(chat)
                    .build();
        }catch (LoginException e){
            e.printStackTrace();
        }
    }
}
