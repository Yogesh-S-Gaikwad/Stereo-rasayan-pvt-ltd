package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Subscriber;

public interface SubscriberRepo extends JpaRepository<Subscriber, Integer>{

}
