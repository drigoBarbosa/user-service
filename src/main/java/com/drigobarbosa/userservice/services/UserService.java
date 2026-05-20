package com.drigobarbosa.userservice.services;

import com.drigobarbosa.userservice.models.UserModel;
import com.drigobarbosa.userservice.producers.UserProducer;
import com.drigobarbosa.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional // Se acontecer algum erro em algum processo, tudo será revertido
    public UserModel saveUser(UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }

}
