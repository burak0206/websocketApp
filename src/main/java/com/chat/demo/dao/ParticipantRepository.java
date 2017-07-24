package com.chat.demo.dao;

import com.chat.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by burakdagli on 24.07.2017.
 */
@Repository
public class ParticipantRepository {

    private Map<String, User> activeSessions = new ConcurrentHashMap<>();

    public void add(String sessionId, User user) {
        activeSessions.put(sessionId, user);
    }

    public User getParticipant(String sessionId) {
        return activeSessions.get(sessionId);
    }

    public void removeParticipant(String sessionId) {
        activeSessions.remove(sessionId);
    }

    public Map<String, User> getActiveSessions() {
        return activeSessions;
    }

    public void setActiveSessions(Map<String, User> activeSessions) {
        this.activeSessions = activeSessions;
    }

}
