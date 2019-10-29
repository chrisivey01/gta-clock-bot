package com.chrisivey.clockbot.repository;

import com.chrisivey.clockbot.entity.GtaEms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GtaEmsRepo extends JpaRepository<GtaEms, Integer> {
    GtaEms findByDiscordUid(String discordUid);
}
