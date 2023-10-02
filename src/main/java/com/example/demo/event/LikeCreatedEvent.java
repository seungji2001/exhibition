package com.example.demo.event;

import com.example.demo.domain.LikeEntity;
import org.springframework.context.ApplicationEvent;

public class LikeCreatedEvent extends ApplicationEvent {

    private LikeEntity like;

    public LikeCreatedEvent(Object source, LikeEntity like) {
        super(source);
        this.like = like;
    }

    public LikeEntity getLike() {
        return like;
    }

}
