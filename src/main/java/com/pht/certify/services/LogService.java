package com.pht.certify.services;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pht.certify.model.Log;
import com.pht.certify.repository.LogRepo;

@Service
public class LogService {

    @Autowired
    private LogRepo logRepo;

    public void save(String action, String message, LocalDateTime timestamp, String userId) {
        Log log = new Log();
        log.setAction(action);
        log.setId(userId);
        log.setTimestamp(timestamp);
        log.setMessage(message);
        logRepo.save(log);
    }

    public long getLogCount() {
        return logRepo.count();
    }

}