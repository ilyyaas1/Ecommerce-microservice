package org.ilyass.ecommerce.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ilyass.ecommerce.dtos.PaymentRequest;
import org.ilyass.ecommerce.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Integer createPayment(
            @RequestBody @Valid PaymentRequest paymentRequest
    ){
        return paymentService.createPayment(paymentRequest);
    }






}
