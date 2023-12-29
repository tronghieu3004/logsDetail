package com.lifesup.log.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "api_key")
    private String apiKey;
    @Column(name = "model")
    private String model;
    @Column(name = "promt_tokens")
    private Integer promtTokens;
    @Column(name = "promt_cost")
    private Double promtCost;
    @Column(name = "completion_tokens")
    private Integer completionTokens;
    @Column(name = "completion_cost")
    private Double completionCost;
    @Column(name = "total_tokens")
    private Integer totalTokens;
    @Column(name = "total_cost")
    private Double totalCost;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci", name = "full_message")
    private String fullMessage;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci")
    private String promt;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci", name = "user_message")
    private String userMessage;
    @Column(columnDefinition = "TEXT CHARACTER SET utf8 COLLATE utf8_unicode_ci")
    private String reply;
    @CreationTimestamp
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime createdAt;
    @CreationTimestamp
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "LogEntity" + id +
                "{id=" + id +
                ", apiKey='" + apiKey + '\'' +
                ", model='" + model + '\'' +
                ", promtTokens=" + promtTokens +
                ", promtCost=" + promtCost +
                ", completionTokens=" + completionTokens +
                ", completionCost=" + completionCost +
                ", totalTokens=" + totalTokens +
                ", totalCost=" + totalCost +
                ", fullMessage='" + fullMessage + '\'' +
                ", promt='" + promt + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", reply='" + reply + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
