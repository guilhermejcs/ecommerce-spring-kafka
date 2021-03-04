package com.ecommerce.checkout.service;

import com.ecommerce.checkout.entity.CheckoutEntity;
import com.ecommerce.checkout.repository.CheckoutRepository;
import com.ecommerce.checkout.resource.CheckoutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .build();
        return Optional.of(checkoutRepository.save(checkoutEntity));
    }
}
