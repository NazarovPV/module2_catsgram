package ru.yandex.practicum.catsgram.controller;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException(String message){
        super(message);
    }
}
