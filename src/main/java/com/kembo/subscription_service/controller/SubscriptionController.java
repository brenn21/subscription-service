package com.kembo.subscription_service.controller;

import com.kembo.subscription_service.model.Subscription;
import com.kembo.subscription_service.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(
            @PathVariable Long userId,
            @RequestBody Subscription request
    ) {
        Subscription created = subscriptionService.addSubscription(userId, request);
        return ResponseEntity.ok(created);
    }


    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptions(
            @PathVariable Long userId
    ) {
        List<Subscription> all = subscriptionService.getAllSubscriptions()
                .stream()
                .filter(s -> s.getUser().getId().equals(userId))
                .toList();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<String> deleteSubscription(
            @PathVariable Long subscriptionId
    ) {
        String result = subscriptionService.deleteSubscription(subscriptionId);
        return ResponseEntity.ok(result);
    }
}

