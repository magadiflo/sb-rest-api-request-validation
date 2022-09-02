package com.magadiflo.app.service;

import com.magadiflo.app.domain.User;

import java.util.List;

public interface IUserService {

    List<User> listAll();

    User save(User user);

}
