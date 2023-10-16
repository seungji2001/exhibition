package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.convert.ReadingConverter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Long id;

    private String writer;

    private String content;
}
