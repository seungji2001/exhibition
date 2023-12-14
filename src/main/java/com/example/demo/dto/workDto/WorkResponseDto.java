package com.example.demo.dto.workDto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class WorkResponseDto {

//    @Builder --v1
//    @Data
//    public static class getWork{
//        private String title;
//        private String imgUrl;
//        private String contents;
//        private String name; //작품을 창작한 멤버의 이름
//    }

    @Builder
    @Data
    public static class getWorkThumbnail{
        private String title;
        private String imgUrl;
        private String contents;
        private String name; //작품을 창작한 멤버의 이름
        private Long viewCount;
    }

    @Builder
    @Data
    public static class getWork{
        private Long work_id;
        private String title;
        private String imgUrl;
        private String contents;
        private String supporter_name;
        private Long view;
        private List<Member> viewList;
        private int likeCount;
    }

    @Builder
    @Data
    public static class getAllWorks{
        private Long work_id;
        private String title;
        private String imgUrl;
        private String contents;
        private Long view;
        private String author;
        private Long memberId;
    }
}
