package com.avi.productservice.services;

import com.avi.productservice.dtos.FakeStoreProductDto;
import com.avi.productservice.exceptions.ProductNotFoundException;
import com.avi.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate; //using this, you will be able to call 3rd party apis

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        ArrayList<Product> products = new ArrayList<>();

        assert fakeStoreProductDtos != null;
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public Product getSingleProduct(long id) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product with id " + id + " is not present with the service. It's an invalid id");
        }

        if(fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful()){
            return fakeStoreProductDto.toProduct();
        }

        return null;
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, Double price) {

        FakeStoreProductDto fakeStoreProductDtoResponse
                = restTemplate.postForObject("https://fakestoreapi.com/products",
                new FakeStoreProductDto(title, description, image, category, price)
                , FakeStoreProductDto.class);

        assert fakeStoreProductDtoResponse != null;
        return fakeStoreProductDtoResponse.toProduct();
    }


}
