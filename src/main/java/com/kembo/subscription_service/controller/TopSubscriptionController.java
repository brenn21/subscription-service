package com.kembo.subscription_service.controller;

import com.kembo.subscription_service.model.Subscription;
import com.kembo.subscription_service.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class TopSubscriptionController {

    private final SubscriptionService subscriptionService;

    public TopSubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/top")
    public ResponseEntity<List<String>> getTopSubscriptions() {
        List<Object[]> result = subscriptionService.getTopSubscriptions();

        List<String> topServices = result.stream()
                .map(row -> row[0].toString())
                .toList();

        return ResponseEntity.ok(topServices);
    }
}
