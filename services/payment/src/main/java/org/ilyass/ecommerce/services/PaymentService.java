package org.ilyass.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.ilyass.ecommerce.Repositories.PaymentRepository;
import org.ilyass.ecommerce.dtos.PaymentRequest;
import org.ilyass.ecommerce.dtos.PaymentResponse;
import org.ilyass.ecommerce.mappers.PaymentMapper;
import org.ilyass.ecommerce.notification.NotificationProducer;
import org.ilyass.ecommerce.notification.PaymentNotificationRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment = paymentRepository.save(paymentMapper.paymentRequestToPayment(paymentRequest));

        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.getOrderReference(),
                        paymentRequest.getAmount(),
                        paymentRequest.getPaymentMethod(),
                        paymentRequest.getCustomer().getFirstName(),
                        paymentRequest.getCustomer().getLastName(),
                        paymentRequest.getCustomer().getEmail()
                )
        );

        return payment.getId();
    }
}
