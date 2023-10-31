package com.example.demo.event;

import com.example.demo.domain.WorkLike;
import com.example.demo.domain.WorkLike;
import org.springframework.context.ApplicationEvent;

public class LikeCreatedEvent extends ApplicationEvent {

    private WorkLike like;

    public LikeCreatedEvent(Object source, WorkLike like) {
        super(source);
        this.like = like;
    }

    public WorkLike getLike() {
        return like;
    }

}
