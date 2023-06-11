package com.danielblanco.supplierintegrations.domain.mapper;

import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.valueobjects.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogActivityMapper {

    public LogActivity stringToLogActivity(String input) throws Exception {

        String[] values = input.split(",");
        return LogActivity.builder()
                .id(new LogId())
                .IP(new LogIP(values[0]))
                .date(new LogDate(values[1]))
                .action(LogAction.valueOf(values[2]))
                .userName(new UserName(values[3]))
                .build();
    }
}
