package com.example.auth.controllers;

import com.example.auth.domain.Review.Review;
import com.example.auth.services.AbstractService;
import com.example.auth.services.impl.ReviewImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("review")
public class ReviewController extends RestControllerImpl<Review> {

    private final ReviewImpl service;

    public ReviewController(ReviewImpl service) {
        this.service = service;
    }

    @Override
    protected AbstractService<Review> getService() {
        return this.service;
    }
}

