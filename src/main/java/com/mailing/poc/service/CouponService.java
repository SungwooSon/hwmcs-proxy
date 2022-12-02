package com.mailing.poc.service;

import com.mailing.poc.domain.Coupon;
import com.mailing.poc.repository.ContentRepository;
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
    private final ContentRepository contentRepository;

    /**
     * 신청 수량 만큼 쿠폰을 발급합니다.
     * 사용여부가 0 인 값을 발급합니다.
     *
     * 발급이 완료된 쿠폰은 사용여부 1, thirdPartyEmail, 사용일시를 update 합니다.
     * @param quantity
     * @param talingId
     */

    @Transactional
    public List<Coupon> issueAndMakeUse(Integer quantity, String talingId, Integer purchaseContentId) {

        if(!isHanhwaUser(talingId)) {
            return List.of(new Coupon(-1l, "abcd123", 1, talingId, LocalDateTime.now(), LocalDateTime.now(), purchaseContentId));
        }

        List<Coupon> coupons = couponRepository.findAll();
        List<Coupon> issuedCoupons = new ArrayList<>();
        int count = quantity;

        for (Coupon coupon : coupons) {
            if (coupon.getUseYn() == 0) {
                issuedCoupons.add(coupon);
                makeUsedCoupn(coupon, talingId, purchaseContentId);
                count--;
            }

            if(count == 0) {
                break;
            }
        }
        return issuedCoupons;
    }

    private boolean isHanhwaUser(String talingId) {
        if(talingId.charAt(0) != 'h') {
            return false; // h로 시작하지 않으면 한화생명 계정이 아님.
        }
        return true;
    }

    private void makeUsedCoupn(Coupon coupon, final String talingId, Integer purchaseContentId) {
        coupon.setUseYn(1);
        coupon.setThirdPartyEmail(talingId);
        coupon.setUsedAt(LocalDateTime.now());
        coupon.setPurchaseContentId(purchaseContentId);
        couponRepository.save(coupon);
    }

    public String getPurchaseContentId(String thirdPartyEmail, String couponNo) {
        return couponRepository.findPurchaseContentId(thirdPartyEmail, couponNo);
    }
    @Transactional
    public void checkSoldOut() {
        int remainCouponCount = couponRepository.findCouponByUseYn(0).size();
        remainCouponCount = 9;
        if(remainCouponCount < 10) {
            contentRepository.updateContentPrice();
        }
    }
}
