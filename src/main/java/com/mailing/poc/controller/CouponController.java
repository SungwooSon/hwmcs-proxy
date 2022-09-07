package com.mailing.poc.controller;

import com.mailing.poc.domain.Coupon;
import com.mailing.poc.response.CouponResponseDto;
import com.mailing.poc.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taling")
@RequiredArgsConstructor
@Slf4j
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/coupons")
    public CouponResponseDto issueCoupons(Integer quantity, String talingId) {

        List<Coupon> coupons = couponService.issueAndMakeUse(quantity, talingId);

        return new CouponResponseDto(coupons);
    }

    // 사용여부 모두 1, 사용자 expried, 사용일시 now 처리하는 api 추가
    // update coupon set useYn = 'Y', thirdParty

    //신규 쿠폰을 등록하는 api 추가
    // insert into coupon(couponNo) values('asdfasdf');

    //탈잉 콘텐츠 사용 스케쥴러 추가

}
