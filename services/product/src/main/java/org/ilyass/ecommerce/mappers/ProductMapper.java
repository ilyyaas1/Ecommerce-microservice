package org.ilyass.ecommerce.mappers;



import org.ilyass.ecommerce.dtos.ProductPurchaseResponse;
import org.ilyass.ecommerce.dtos.ProductRequest;
import org.ilyass.ecommerce.dtos.ProductResponse;
import org.ilyass.ecommerce.entities.Category;
import org.ilyass.ecommerce.entities.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

        public Product productRequestToProduct(ProductRequest productRequest) {
            return Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .availableQuantity(productRequest.getAvailableQuantity())
                    .category(Category.builder().id(productRequest.getCategoryId()).build())
                    .build();
        }
        public ProductResponse productToProductResponse(Product product) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setAvailableQuantity(product.getAvailableQuantity());
            productResponse.setPrice(product.getPrice());
            productResponse.setCategoryId(product.getCategory().getId());
            return productResponse;
        }

        public ProductPurchaseResponse toProductPurchaseResponse(Product product,double quantity) {
            return new ProductPurchaseResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    quantity
            );

        }

}
