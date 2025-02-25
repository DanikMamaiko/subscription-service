package com.example.sbertestproject.service.impl;

import com.example.sbertestproject.entity.Subscription;
import com.example.sbertestproject.entity.SubscriptionType;
import com.example.sbertestproject.entity.User;
import com.example.sbertestproject.repository.SubscriptionRepository;
import com.example.sbertestproject.repository.UserRepository;
import com.example.sbertestproject.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public Subscription saveSubscription(Long userId, Subscription subscription) {
        log.info("Attempting to save a new subscription for user ID: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User not found"));

        subscription.setUser(user);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        log.info("Subscription saved successfully with ID: {}", savedSubscription.getId());
        return savedSubscription;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Subscription> getAllUserSubscriptions(Long userId) {
        log.info("Fetching all subscriptions for user ID: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User not found"));
        Set<Subscription> subscriptions = user.getSubscriptions();
        log.info("Found {} subscriptions for user ID: {}", subscriptions.size(), userId);
        return subscriptions;
    }

    @Override
    @Transactional
    public void deleteSubscription(Long userId, Long subscriptionId) {
        log.info("Attempting to delete subscription ID: {} for user ID: {}", subscriptionId, userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User not found"));
        Subscription subscription = user.getSubscriptions().stream()
                .filter(sub -> sub.getId().equals(subscriptionId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Subscription not found"));
        subscriptionRepository.delete(subscription);
        log.info("Subscription ID: {} successfully deleted for user ID: {}", subscriptionId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Subscription> getAllSubscriptions(Long userId, int page, int size) {
        log.info("Fetching page {} of size {} for subscriptions of user ID: {}", page, size, userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Subscription> subscriptions = subscriptionRepository.findAll(pageable);
        log.info("Found {} subscriptions on requested page", subscriptions.getTotalElements());
        return subscriptions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionType> getThreeMostUsedSubscriptions() {
        log.info("Fetching three most used subscriptions");
        return subscriptionRepository.findTop3MostPopularSubscriptions();
    }
}
