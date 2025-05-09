package com.kembo.subscription_service.service;

import com.kembo.subscription_service.model.User;
import com.kembo.subscription_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            log.info("This user does not exist");
        }
        else
            return Optional.of(new User(
                    optionalUser.get().getId(),
                    optionalUser.get().getFirstName(),
                    optionalUser.get().getLastName(),
                    optionalUser.get().getEmail(),
                    optionalUser.get().getPhoneNumber(),
                    optionalUser.get().getSubscriptions())
            );

        return optionalUser;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> updatedUser = userRepository.findById(id);
        if (updatedUser.isPresent()) {
            updatedUser.get().setFirstName(user.getFirstName());
            updatedUser.get().setLastName(user.getLastName());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setPhoneNumber(user.getPhoneNumber());
            updatedUser.get().setSubscriptions(user.getSubscriptions());

            userRepository.save(updatedUser.get());
        }
        return user;
    }

    public String delete(Long id) {
        userRepository.findById(id);
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User with ID: {} was deleted ", id);
            return String.format("User with ID: %s was deleted ", id);
        }
        return String.format("Not Found User with ID: %s", id);
    }
}


