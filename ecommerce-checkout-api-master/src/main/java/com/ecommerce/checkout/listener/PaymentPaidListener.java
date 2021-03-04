package com.ecommerce.checkout.listener;

import com.ecommerce.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.event.PaymentCreatedEvent;
import com.ecommerce.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.streaming.PaymentPaidSink;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event){
        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus((CheckoutEntity.Status.APPROVED));
        checkoutRepository.save(checkoutEntity);
    }
}
