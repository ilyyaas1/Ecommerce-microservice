package org.ilyass.ecommerce.Repositories;

import org.ilyass.ecommerce.dtos.OrderLineResponse;
import org.ilyass.ecommerce.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
