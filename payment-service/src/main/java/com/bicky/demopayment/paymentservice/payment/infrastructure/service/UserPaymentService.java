package com.bicky.demopayment.paymentservice.payment.infrastructure.service;

import com.bicky.demopayment.paymentservice.payment.domain.entity.PaymentMethod;
import com.bicky.demopayment.paymentservice.payment.domain.entity.User;
import com.bicky.demopayment.paymentservice.payment.domain.entity.UserPaymentProvider;
import com.bicky.demopayment.paymentservice.payment.domain.repository.PaymentMethodRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserPaymentProviderRepository;
import com.bicky.demopayment.paymentservice.payment.domain.repository.UserRepository;
import com.bicky.demopayment.paymentservice.shared.service.PaymentService;
import com.bicky.demopayment.paymentservice.shared.utils.SecurityUtils;
import com.bicky.demopayment.paymentservice.shared.valueobject.AccountDetails;
import com.bicky.demopayment.paymentservice.shared.valueobject.CardDetail;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentMethodId;
import com.bicky.demopayment.paymentservice.shared.valueobject.PaymentProvider;
import com.bicky.demopayment.paymentservice.stripe.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPaymentService {

    private final UserRepository userRepository;
    private final UserPaymentProviderRepository userPaymentProviderRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public boolean createPaymentProviderCustomer(User user, PaymentProvider paymentProvider) {
        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(paymentProvider);
        UserPaymentProvider userPaymentProvider  = paymentServiceProvider.createCustomer(user);
        userPaymentProviderRepository.create(userPaymentProvider);
        return true;
    }

    public boolean savePaymentMethod(AccountDetails accountDetails, PaymentProvider provider) {
        String userId = SecurityUtils.getCurrentUserId();
        User user = userRepository.findByKeycloakId(userId);
        UserPaymentProvider userPaymentProvider = userPaymentProviderRepository
                .getUserPaymentProviderBy(user.getId(), provider);

        if (UserPaymentProvider.isEmptyObject(userPaymentProvider)) {
            userPaymentProvider.setPaymentProvider(provider);
            userPaymentProvider.setUser(user);
            if (!createPaymentProviderCustomer(user, provider)) {
                return false;
            }
        }

        CardDetail cardDetail = CardDetail.builder()
                .cardNumber(accountDetails.getCardNumber())
                .cardHolderName(accountDetails.getCardHolderName())
                .expiryMonth(accountDetails.getExpiryMonth())
                .expiryYear(accountDetails.getExpiryYear())
                .build();

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

        PaymentServiceProvider paymentServiceProvider = new PaymentServiceProvider(provider);
        PaymentMethodId paymentMethodId = paymentServiceProvider.createPaymentMethod(accountDetails);
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
