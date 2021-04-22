package com.senin.demo.util;

import com.senin.demo.exception.IncorrectUserNameException;
import com.senin.demo.exception.IncorrectIdRuntimeException;

public class UtilityService {
    private static final String ID_EMPTY = "ID can not be empty";
    private static final String ID_POSITIVE = "ID must be positive";
    public static final String ID_CORRECT = "ID must be correct";
    public static final String USERNAME_CORRECT = "UserName must be correct";
    public static final String USERNAME_EMPTY = "UserName can not be empty";


    public static void isIdPositive(Long id) {
        if (id == null && id < 0) {
            throw new IncorrectIdRuntimeException(ID_POSITIVE, ID_EMPTY);
        }
    }

    public static void isNamePresent(String userName){
        if(userName == null){
            throw new IncorrectUserNameException(userName);
        }
    }
}
