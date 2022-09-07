package com.mailing.poc.response;

import com.mailing.poc.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CouponResponseDto {

    List<Coupon> coupons;

}
