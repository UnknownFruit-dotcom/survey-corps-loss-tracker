package com.fruit.scouts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "scouts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Scout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Rank rank = Rank.MEMBER;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private Position position;

    @Column(nullable = false, name = "total_missions")
    private Integer totalMissions= 0;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ScoutStatus status = ScoutStatus.ACTIVE;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
