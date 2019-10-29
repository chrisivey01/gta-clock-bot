package com.chrisivey.clockbot.repository;

import com.chrisivey.clockbot.entity.GtaPolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GtaPoliceRepo extends JpaRepository<GtaPolice, Integer> {
    GtaPolice findByDiscordUid(String discordUid);
}
