package com.example.auth.services.impl;

import com.example.auth.domain.Review.Review;
import com.example.auth.repositories.ReviewRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewImpl extends AbstractClassImpl<Review> {

    private final ReviewRepository repository;

    public ReviewImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Review, String> getRepository() {
        return this.repository;
    }
}
