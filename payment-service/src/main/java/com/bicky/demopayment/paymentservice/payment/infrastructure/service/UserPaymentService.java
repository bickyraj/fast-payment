package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;

@RequiredArgsConstructor
@Service
public class UserPaymentService {

    private final UserRepository userRepository;
    private final UserPaymentProviderRepository userPaymentProviderRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserService userService;

    public boolean createPaymentProviderCustomer(User user, PaymentProvider paymentProvider) {
        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(paymentProvider);
        UserPaymentProvider userPaymentProvider  = paymentServiceProvider.createCustomer(user);
        userPaymentProviderRepository.create(userPaymentProvider);
        return true;
    }

    public boolean savePaymentMethod(PaymentProvider provider, PaymentMethodId paymentMethodId) throws NotFoundException {
        String keycloakId = userService
                .getUser()
                .map(user -> user.getKeycloakId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        User user = userRepository.findByKeycloakId(keycloakId);
        UserPaymentProvider userPaymentProvider = userPaymentProviderRepository
                .getUserPaymentProviderBy(user.getId(), provider);

        if (UserPaymentProvider.isEmptyObject(userPaymentProvider)) {
            userPaymentProvider.setPaymentProvider(provider);
            userPaymentProvider.setUser(user);
            if (!createPaymentProviderCustomer(user, provider)) {
                return false;
            }
            userPaymentProvider = userPaymentProviderRepository
                    .getUserPaymentProviderBy(user.getId(), provider);
        }

        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(provider);
        CardDetail cardDetail = paymentServiceProvider.getCardDetail(paymentMethodId);

        boolean isExistsPaymentMethod = paymentMethodRepository
                .existsBy(
                        user.getId(),
                        provider,
                        cardDetail.getCardNumber().toString(),
                        cardDetail.getExpiryMonth().toString(),
                        cardDetail.getExpiryYear().toString()
                );

        if (isExistsPaymentMethod) {
            return false;
        }

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .paymentMethodId(paymentMethodId.value())
                .paymentProvider(provider)
                .cardDetail(cardDetail)
                .user(user)
                .build();
        paymentMethodRepository.save(paymentMethod);
        return paymentServiceProvider
                .addPaymentMethod(userPaymentProvider.getProviderCustomerId(), paymentMethodId.value());
    }
}
