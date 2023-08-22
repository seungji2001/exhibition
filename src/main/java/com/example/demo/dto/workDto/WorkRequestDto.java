package com.example.demo.dto.workDto;

import lombok.Builder;
import lombok.Data;

public class WorkRequestDto {

    //version 1
    @Builder
    @Data
    public static class registNewWork{
        private String name; //작품을 창작한 멤버의 이름
        private String title;
        private String contents;
    }

    @Builder
    @Data
    public static class registSupporterWork{
        private String img_url;
        private String content;
        private String title;
    }


}
