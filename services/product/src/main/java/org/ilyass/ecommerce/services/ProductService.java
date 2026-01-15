package org.ilyass.ecommerce.services;

import org.ilyass.ecommerce.dtos.ProductPurchaseResponse;
import org.ilyass.ecommerce.dtos.ProductPurchaseRequest;
import org.ilyass.ecommerce.dtos.ProductRequest;
import org.ilyass.ecommerce.dtos.ProductResponse;
import org.ilyass.ecommerce.entities.Product;
import org.ilyass.ecommerce.mappers.ProductMapper;
import org.ilyass.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;
    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    public List<ProductResponse> getProducts() {
        return repository.findAll().stream().map(productMapper::productToProductResponse).collect(Collectors.toList());
    }
    public ProductResponse getProductById(Integer id) {
         Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
         return productMapper.productToProductResponse(product);
    }
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        return productMapper.productToProductResponse(repository.save(product));
    }
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        Product product = repository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        if(productRequest.getName() != null) {
            product.setName(productRequest.getName());
        }
        if(productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }
        if(productRequest.getPrice() != null) {
            product.setPrice(productRequest.getPrice());

        }
        if(productRequest.getAvailableQuantity() != 0){
            product.setAvailableQuantity(productRequest.getAvailableQuantity());
        }
        return productMapper.productToProductResponse(repository.save(product));
    }
    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productsId = request.stream().map(ProductPurchaseRequest::getProductId).toList();
        var storedProducts = repository.findAllByIdInOrderById(productsId);

        if(productsId.size() != storedProducts.size()){
            throw new RuntimeException("Number of products does not match");
        }

        var storedRequest = request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::getProductId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(var i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.getQuantity()){
                throw new RuntimeException("Insufficient quantity");
            }
            var newAvailableQuantity =  product.getAvailableQuantity()-productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product,productRequest.getQuantity()));
        }
        return purchasedProducts;

    }

}
