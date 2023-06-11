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
        new LogActivityBuilder().setIP(IP).setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName(USERNAME).createLogActivity();
    }

    @Test()
    public void createLogActivityIpNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP("").setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName(USERNAME).createLogActivity()
        );
    }
    @Test()
    public void createLogActivityIpFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP("123,,,1683,112,5").setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName(USERNAME).createLogActivity()
        );
    }

    @Test()
    public void createLogActivityDateNullTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP(IP).setDate(null).setAction(LogAction.SIGNIN_SUCCESS).setUserName(USERNAME).createLogActivity()
        );
    }
    @Test()
    public void createLogActivityDateFormatErrorTest(){
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP(IP).setDate("12/23/68").setAction(LogAction.SIGNIN_SUCCESS).setUserName(USERNAME).createLogActivity()
        );
    }

    @Test
    public void createLogUserNameNull() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP(IP).setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName(null).createLogActivity()
        );
    }
    @Test
    public void createLogUserNameEmpty() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP(IP).setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName("").createLogActivity()
        );
    }
    @Test
    public void createLogUserNameFormatError() {
        Exception exception = assertThrows(Exception.class, () ->
                new LogActivityBuilder().setIP(IP).setDate(DATE).setAction(LogAction.SIGNIN_SUCCESS).setUserName("userName").createLogActivity()
        );
    }
}
