package com.magadiflo.app.service.impl;

import com.magadiflo.app.domain.User;
import com.magadiflo.app.repository.IUserRepository;
import com.magadiflo.app.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listAll() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
