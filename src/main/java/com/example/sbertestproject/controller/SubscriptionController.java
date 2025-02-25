package com.example.sbertestproject.controller;

import com.example.sbertestproject.entity.Subscription;
import com.example.sbertestproject.entity.SubscriptionType;
import com.example.sbertestproject.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/users/{id}/subscriptions")
    public Subscription addSubscription(@PathVariable Long id,
                                        @RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(id, subscription);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}/subscriptions")
    public Set<Subscription> getAllSubscriptions(@PathVariable Long id) {
        return subscriptionService.getAllUserSubscriptions(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void deleteSubscription( @PathVariable Long id,
                                    @PathVariable Long sub_id) {
        subscriptionService.deleteSubscription(id, sub_id);
    }

    @GetMapping
    public Page<Subscription> getAllSubscriptions(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return subscriptionService.getAllSubscriptions(userId, page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/subscriptions/top")
    public List<SubscriptionType> getThreeMostUsedSubscriptions() {
        return subscriptionService.getThreeMostUsedSubscriptions();
    }
}
