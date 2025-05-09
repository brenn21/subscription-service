package com.kembo.subscription_service.repository;

import com.kembo.subscription_service.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Long>, JpaRepository<Subscription, Long> {

    @Query("SELECT s.serviceName, COUNT(s) as cnt FROM Subscription s GROUP BY s.serviceName ORDER BY cnt DESC")
    List<Object[]> findTopSubscriptions(Pageable pageable);
}