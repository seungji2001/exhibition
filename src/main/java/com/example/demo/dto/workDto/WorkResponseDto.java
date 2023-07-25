package com.example.demo.dto.workDto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

public class WorkResponseDto {
    @Builder
    @Data
    public static class getWork{
        private String title;
        private String imgUrl;
        private String contents;
        private String name; //작품을 창작한 멤버의 이름
    }

    @Builder
    @Data
    public static class getWorkThumbnail{
        private String title;
        private String imgUrl;
        private String contents;
        private String name; //작품을 창작한 멤버의 이름
        private Long viewCount;
    }
}
