package com.mailing.poc.service;

import com.mailing.poc.domain.Coupon;
import com.mailing.poc.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    /**
     * 신청 수량 만큼 쿠폰을 발급합니다.
     * 사용여부가 0 인 값을 발급합니다.
     *
     * 발급이 완료된 쿠폰은 사용여부 1, thirdPartyEmail, 사용일시를 update 합니다.
     * @param quantity
     * @param talingId
     */

    @Transactional
    public List<Coupon> issueAndMakeUse(Integer quantity, String talingId) {

        List<Coupon> coupons = couponRepository.findAll();
        List<Coupon> issuedCoupons = new ArrayList<>();
        int count = quantity;

        for (Coupon coupon : coupons) {
            if (coupon.getUseYn() == 0) {
                issuedCoupons.add(coupon);
                makeUsedCoupn(coupon, talingId);
                count--;
            }

            if(count == 0) {
                break;
            }
        }

        return issuedCoupons;
    }

    private void makeUsedCoupn(Coupon coupon, final String talingId) {
        coupon.setUseYn(1);
        coupon.setThirdPartyEmail(talingId);
        coupon.setUsedAt(LocalDateTime.now());
        couponRepository.save(coupon);
    }
}
