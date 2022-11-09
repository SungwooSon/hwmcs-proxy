package com.mailing.poc.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "Coupon", schema = "poc")
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String couponNo;
    private Integer useYn;
    private String thirdPartyEmail;
    private LocalDateTime usedAt;
    private LocalDateTime createAt;
    private Integer purchaseContentId;

    public Coupon(String couponNo, Integer useYn, LocalDateTime createAt) {
        this.couponNo = couponNo;
        this.useYn = useYn;
        this.createAt = createAt;
    }
}
