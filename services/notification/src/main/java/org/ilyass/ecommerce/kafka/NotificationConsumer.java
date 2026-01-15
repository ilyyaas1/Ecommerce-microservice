package org.ilyass.ecommerce.kafka;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.ilyass.ecommerce.email.EmailService;
import org.ilyass.ecommerce.kafka.order.OrderConfirmation;
import org.ilyass.ecommerce.kafka.payment.PaymentConfirmation;
import org.ilyass.ecommerce.notification.Notification;
import org.ilyass.ecommerce.notification.NotificationRepository;
import org.ilyass.ecommerce.notification.NotificationType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics="payment-topic")
    public void consumePaymentNotification(PaymentConfirmation confirmation) throws MessagingException {
        log.info("Received Payment Confirmation: {}", confirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(confirmation)
                        .build()
        );
        var customerName= confirmation.customerFirstname() + " " + confirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                confirmation.customerEmail(),
                customerName,
                confirmation.amount(),
                confirmation.orderReference()
        );
    }
    @KafkaListener(topics="order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation confirmation) throws MessagingException {
        log.info("Received Payment Confirmation: {}", confirmation);
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(confirmation)
                        .build()
        );
        var customerName= confirmation.customer().firstname() + " " + confirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                confirmation.customer().email(),
                customerName,
                confirmation.totalAmount(),
                confirmation.orderReference(),
                confirmation.products()

        );


    }





}
