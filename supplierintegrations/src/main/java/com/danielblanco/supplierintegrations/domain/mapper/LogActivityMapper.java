package com.danielblanco.supplierintegrations.domain.mapper;

import com.danielblanco.supplierintegrations.domain.entity.LogActivity;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogDate;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogIP;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogId;
import com.danielblanco.supplierintegrations.domain.valueobjects.UserName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogActivityMapper {

    public LogActivity stringToLogActivity(String input) throws Exception {
        log.info("[LogActivityMapper] stringToLogActivity start...");
        log.info("[LogActivityMapper] stringToLogActivity input: {}", input);
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
