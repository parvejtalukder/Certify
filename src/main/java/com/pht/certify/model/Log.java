package com.pht.certify.model;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Log {

    @Id
    private String id;
    private String action;   
    private String message;  
    private LocalDateTime timestamp;
    private String time;
    private String userId;

    // Log(){

    // }

    // Log(
    // String action, 
    // String message,String time, String userId) {
    //     this.setAction(action);
    //     this.setMessage(message);
    //     this.setTime(time);
    //     this.setUserId(userId);
    // }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }


}
