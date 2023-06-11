package com.danielblanco.supplierintegrations.domain.entity;

import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogDate;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogIP;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogId;
import com.danielblanco.supplierintegrations.domain.valueobjects.UserName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@Getter
@Builder
public class LogActivity {

    private LogId id;
    private LogIP IP;
    private LogDate date;
    private LogAction action;
    private UserName userName;

    @Override
    public String toString() {
        return "LogActivity{" +
                "id=" + id.getValue() +
                ", IP=" + IP.getValue() +
                ", date=" + date.getValueFormat() +
                ", action=" + action +
                ", userName=" + userName.getValue() +
                '}';
    }
}
