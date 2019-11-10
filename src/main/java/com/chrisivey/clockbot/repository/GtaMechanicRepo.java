package com.chrisivey.clockbot.repository;

import com.chrisivey.clockbot.entity.GtaMechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GtaMechanicRepo extends JpaRepository<GtaMechanic, Integer> {
    GtaMechanic findByDiscordUid(String discordUid);
}
