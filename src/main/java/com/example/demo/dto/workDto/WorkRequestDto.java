package com.example.demo.dto.workDto;

import lombok.Builder;
import lombok.Data;

public class WorkRequestDto {

    @Builder
    @Data
    public static class registNewWork{
        private String name; //작품을 창작한 멤버의 이름
        private String title;
        private String contents;
    }

}
