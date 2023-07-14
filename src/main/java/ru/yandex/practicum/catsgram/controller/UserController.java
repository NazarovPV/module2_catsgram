package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class UserController {
    public static HashMap<String, User> users = new HashMap<>();

    @GetMapping("/users")
    public ArrayList <User> getUsers(){
        ArrayList<User> usersData = new ArrayList<>();
        for (String s : users.keySet()) {
            usersData.add(users.get(s));
        }
        return usersData;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        if (users.containsKey(user.getEmail())){
            throw new UserAlreadyExistException("Пользователь с таким Email уже существует ");
        } else if (user.getEmail()==null || user.getEmail().equals("")) {
            throw new InvalidEmailException("Указан неверный Email");
        }else{
        users.put(user.getEmail(),user);
        }


    }
    @PutMapping("/users")
    public void updateOrCreateUser(@RequestBody User user) throws InvalidEmailException {
        if (user.getEmail()==null || user.getEmail().equals("")) {
            throw new InvalidEmailException("Указан неверный Email");
        }
        if (users.containsKey(user.getEmail())){
            users.remove(user.getEmail());
            users.put(user.getEmail(), user);
        }
    }

}
