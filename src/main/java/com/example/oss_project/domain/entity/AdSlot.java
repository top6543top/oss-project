package com.example.oss_project.domain.entity;

import com.example.oss_project.domain.type.AdSlotStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ad_slot")
public class AdSlot {
    @Id
    @Column(name = "ad_slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adSlotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "ad_slot_status")
    private AdSlotStatus adSlotStatus = AdSlotStatus.BEFORE_BIDDING;

    @Column(name = "ad_slot_name")
    private String adSlotName;

    @Column(name = "ad_start_date")
    private LocalDateTime startDate;

    @OneToMany(mappedBy = "adSlot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CvInfo> cvInfos;

    @OneToMany(mappedBy = "adSlot", cascade = CascadeType.ALL)
    private List<MinPrice> minPrices = new ArrayList<>();

    @Column(name = "ad_slot_bid_start_time")
    private LocalDateTime bidStartTime ;

    @Column(name = "ad_slot_bid_end_time")
    private LocalDateTime bidEndTime ;

    @Column(name = "ad_slot_description")
    private String description;

    @Column(name = "ad_image_url")
    private String imageUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "ad_slot_width")
    private Long width;

    @Column(name = "ad_slot_height")
    private Long height;

    @Column(name = "ad_loc_x")
    private Double locX;

    @Column(name = "ad_loc_y")
    private Double locY;


    public void setBidTime() {
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime now = LocalDateTime.of(2025, 6, 1, 8, 0); // 2025년 6월 2일 12:00로 고정
        LocalDate tomorrow = now.toLocalDate().plusDays(1);

        this.bidStartTime = LocalDateTime.of(tomorrow, LocalTime.MIDNIGHT); // 내일 00:00
        this.bidEndTime = bidStartTime.plusDays(1); // 모레 00:00
    }
}