package com.danielblanco.supplierintegrations.domain.entity;

import com.danielblanco.supplierintegrations.domain.valueobjects.LogAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class LogActivityTest {

    private static final String IP = "80.238.9.179";
    private static final String DATE = "133612947";
    private static final String USERNAME = "Will.Smith";


    @Test
    public void createLogActivityOK() {
        new LogActivity( IP, DATE, LogAction.SIGNIN_SUCCESS, USERNAME);
    }

    @Test()
    public void createLogActivityIpNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity("", DATE, LogAction.SIGNIN_SUCCESS, USERNAME)
        );
    }
    @Test()
    public void createLogActivityIpFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity("123,,,1683,112,5", DATE, LogAction.SIGNIN_SUCCESS, USERNAME)
        );
    }

    @Test()
    public void createLogActivityDateNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity(IP, null, LogAction.SIGNIN_SUCCESS, USERNAME)
        );
    }
    @Test()
    public void createLogActivityDateFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity(IP, "12/23/68", LogAction.SIGNIN_SUCCESS, USERNAME)
        );
    }

    @Test
    public void createLogUserNameNull() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity(IP, DATE, LogAction.SIGNIN_SUCCESS, null)
        );
    }
    @Test
    public void createLogUserNameEmpty() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity(IP, DATE, LogAction.SIGNIN_SUCCESS, "")
        );
    }
    @Test
    public void createLogUserNameFormatError() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivity(IP, DATE, LogAction.SIGNIN_SUCCESS, "userName")
        );
    }
}
