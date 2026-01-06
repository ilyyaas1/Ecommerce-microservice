package org.ilyass.ecommerce.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilyass.ecommerce.kafka.order.Product;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String to,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,UTF_8.name());
        mimeMessageHelper.setFrom("noreply@ilyass.ecommerce.com");
        final String TemplateName = EmailTemplate.PAYMENT_CONFIRMATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("amount", amount);
        model.put("orderReference",orderReference);
        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(EmailTemplate.PAYMENT_CONFIRMATION.getSubject());
        try{
            String html = templateEngine.process(TemplateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(to);
            emailSender.send(mimeMessage);
            log.info("Email sent to %s successfully with TemplateName: %s", to, TemplateName);
        }catch (MessagingException e){
            e.printStackTrace();
            log.warn("Email sent to %s failed with TemplateName: %s", to, TemplateName);


        }
    }
    @Async
    public void sendOrderConfirmationEmail(
            String to,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED,UTF_8.name());
        mimeMessageHelper.setFrom("noreply@ilyass.ecommerce.com");
        final String TemplateName = EmailTemplate.ORDER_CONFIRMATION.getTemplate();
        Map<String, Object> model = new HashMap<>();
        model.put("customerName", customerName);
        model.put("totalAmount", amount);
        model.put("orderReference",orderReference);
        model.put("products", products);
        Context context = new Context();
        context.setVariables(model);
        mimeMessageHelper.setSubject(EmailTemplate.ORDER_CONFIRMATION.getSubject());
        try{
            String html = templateEngine.process(TemplateName, context);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setTo(to);
            emailSender.send(mimeMessage);
            log.info("Email sent to %s successfully with TemplateName: %s", to, TemplateName);
        }catch (MessagingException e){
            e.printStackTrace();
            log.warn("Email sent to %s failed with TemplateName: %s", to, TemplateName);


        }
    }


}
