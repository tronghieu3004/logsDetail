package com.lifesup.log.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tbl_log")
public class LogEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    private String api_key;
    private String model;
    private Integer promt_tokens;
    private Double promt_cost;
    private Integer completion_tokens;
    private Double completion_cost;
    private Integer total_tokens;
    private Double total_cost;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci" )
    private String full_message;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci" )
    private String promt;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci" )
    private String user_message;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci" )
    private String reply;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;
}
