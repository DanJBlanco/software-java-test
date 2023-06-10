package com.danielblanco.supplierintegrations.domain.entity;

import com.danielblanco.supplierintegrations.domain.valueobjects.*;
import lombok.Data;

@Data
public class LogActivity {
    private LogId id;
    private LogIP IP;
    private LogDate date;
    private LogAction action;
    private UserName userName;

    public LogActivity(String IP, String date, LogAction action, String userName) {
        this.id = new LogId();
        this.IP = new LogIP(IP);
        this.date = new LogDate(date);
        this.action = action;
        this.userName = new UserName(userName);
    }
}
