package com.example.sbertestproject.service;

import com.example.sbertestproject.entity.Subscription;
import com.example.sbertestproject.entity.SubscriptionType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface SubscriptionService {
    Subscription saveSubscription(Long userId, Subscription subscription);
    Set<Subscription> getAllUserSubscriptions(Long userId);
    void deleteSubscription(Long userId, Long subscriptionId);
    Page<Subscription> getAllSubscriptions(Long userId, int page, int size);
    List<SubscriptionType> getThreeMostUsedSubscriptions();
}
