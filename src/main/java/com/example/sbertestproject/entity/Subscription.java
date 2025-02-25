package com.example.sbertestproject.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType serviceName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
