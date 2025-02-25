package com.example.sbertestproject.repository;

import com.example.sbertestproject.entity.Subscription;
import com.example.sbertestproject.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>,
        PagingAndSortingRepository<Subscription, Long> {
    @Query("SELECT s.serviceName FROM Subscription s GROUP BY s.serviceName ORDER BY COUNT(s) DESC")
    List<SubscriptionType> findTop3MostPopularSubscriptions();
}
