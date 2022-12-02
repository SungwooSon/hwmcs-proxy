package com.mailing.poc.repository;

import com.mailing.poc.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Query(value = "select id from poc.PurchaseContent pc where thirdPartyEmail = :thirdPartyEmail and couponNo = :couponNo", nativeQuery = true)
    public String findPurchaseContentId(@Param(value = "thirdPartyEmail") String thirdPartyEmail, @Param(value = "couponNo") String couponNo);

    public List<Coupon> findCouponByUseYn(@Param("useYn") int useYn);

}
