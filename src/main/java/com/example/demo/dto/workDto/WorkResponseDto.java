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
        private String name;
    }
}
