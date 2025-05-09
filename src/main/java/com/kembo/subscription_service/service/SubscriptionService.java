package com.kembo.subscription_service.service;

import com.kembo.subscription_service.model.Subscription;
import com.kembo.subscription_service.model.User;
import com.kembo.subscription_service.repository.SubscriptionRepository;
import com.kembo.subscription_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription addSubscription(Long userId, Subscription serviceName) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }

        Subscription subscription = new Subscription();

        subscription.setUser(userOptional.get());
        subscription.setServiceName(serviceName.getServiceName());
        return subscriptionRepository.save(subscription);
    }

    public String deleteSubscription(Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            log.info("The subscription with ID: {} was deleted", id);
            return String.format("Subscription with ID: %s deleted", id);
        } else {
            return String.format("Subscription with ID: %s not found", id);
        }
    }


    public List<Object[]> getTopSubscriptions() {
        Pageable topThree = PageRequest.of(0, 3);
        return subscriptionRepository.findTopSubscriptions(topThree);
    }

}
