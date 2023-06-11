package com.danielblanco.supplierintegrations.domain.entity;

import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogDate;
import com.danielblanco.supplierintegrations.domain.valueobjects.LogIP;
import com.danielblanco.supplierintegrations.domain.valueobjects.UserName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class LogActivityTest {

    private static final String IP = "80.238.9.179";
    private static final String DATE = "133612947";
    private static final String USERNAME = "Will.Smith";


    @Test
    public void createLogActivityOK() {
        LogActivity.builder()
                .IP(new LogIP(IP))
                .date(new LogDate(DATE))
                .action(LogAction.SIGNIN_SUCCESS)
                .userName(new UserName(USERNAME))
                .build();
    }

    @Test()
    public void createLogActivityIpNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                LogActivity.builder()
                .IP(new LogIP(""))
                .date(new LogDate(DATE))
                .action(LogAction.SIGNIN_SUCCESS)
                .userName(new UserName(USERNAME))
                .build()
        );
    }
    @Test()
    public void createLogActivityIpFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                LogActivity.builder()
                        .IP(new LogIP("123,,,1683,112,5"))
                        .date(new LogDate(DATE))
                        .action(LogAction.SIGNIN_SUCCESS)
                        .userName(new UserName(USERNAME))
                        .build()
        );
    }

    @Test()
    public void createLogActivityDateNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                LogActivity.builder()
                        .IP(new LogIP(IP))
                        .date(new LogDate(null))
                        .action(LogAction.SIGNIN_SUCCESS)
                        .userName(new UserName(USERNAME))
                        .build()
        );
    }
    @Test()
    public void createLogActivityDateFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                LogActivity.builder()
                        .IP(new LogIP(IP))
                        .date(new LogDate("12/23/68"))
                        .action(LogAction.SIGNIN_SUCCESS)
                        .userName(new UserName(USERNAME))
                        .build()
        );
    }

    @Test
    public void createLogUserNameNull() {
        Exception exception = assertThrows(Exception.class, () ->

                        LogActivity.builder()
                                .IP(new LogIP(IP))
                                .date(new LogDate(DATE))
                                .action(LogAction.SIGNIN_SUCCESS)
                                .userName(new UserName(null))
                                .build()
        );
    }
    @Test
    public void createLogUserNameEmpty() {
        Exception exception = assertThrows(Exception.class, () ->

                        LogActivity.builder()
                                .IP(new LogIP(IP))
                                .date(new LogDate(DATE))
                                .action(LogAction.SIGNIN_SUCCESS)
                                .userName(new UserName(""))
                                .build()
        );
    }
    @Test
    public void createLogUserNameFormatError() {
        Exception exception = assertThrows(Exception.class, () ->

                        LogActivity.builder()
                                .IP(new LogIP(IP))
                                .date(new LogDate(DATE))
                                .action(LogAction.SIGNIN_SUCCESS)
                                .userName(new UserName("userName"))
                                .build()
        );
    }
}
