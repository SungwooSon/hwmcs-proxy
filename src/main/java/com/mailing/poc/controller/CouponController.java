package com.mailing.poc.controller;

import com.mailing.poc.domain.Coupon;
import com.mailing.poc.response.CouponResponseDto;
import com.mailing.poc.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/taling")
@RequiredArgsConstructor
@Slf4j
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/coupons")
    public CouponResponseDto issueCoupons(Integer quantity, String talingId, Integer purchaseContentId) {

        List<Coupon> coupons = couponService.issueAndMakeUse(quantity, talingId, purchaseContentId);

        return new CouponResponseDto(coupons);
    }

    // 사용여부 모두 1, 사용자 expried, 사용일시 now 처리하는 api 추가
    // update coupon set useYn = 'Y', thirdParty

    //신규 쿠폰을 등록하는 api 추가
    // insert into coupon(couponNo) values('asdfasdf')


    //탈잉 콘텐츠 사용 스케쥴러 추가
    public void complete() {
        // 엑셀자료를 읽는다.

        String thirdPartyEmail = "";
        String couponNo = "";

        //select id, contentId
        // from poc.PurchaseContent
        // where thirdPartyEmail = 'ht011'
        //   and info like '%' + couponNo(엑셀) + '%'
        //;

        String purchaseContentId = couponService.getPurchaseContentId(thirdPartyEmail, couponNo);

        if(purchaseContentId.isBlank() || purchaseContentId.isEmpty()) {
            throw new IllegalStateException("조회된 구매내역이 없음");
        }

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response
                = restTemplate.getForEntity("https://api.mcspoc.net/purchaseContent/"+purchaseContentId+"/forceUse", String.class);

    }

}
