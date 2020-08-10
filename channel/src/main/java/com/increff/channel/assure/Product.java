package com.increff.channel.assure;

import com.increff.commons.data.ClientData;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.ProductForm;
import com.increff.commons.util.AbstractRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;

@Component
public class Product extends AbstractRestTemplate {

    @Value("${server.url}")
    private String SERVER_URL;

    public ProductData getByClientNameAndClientSkuId(String clientSkuId, String clientName) {
        ProductForm form= new ProductForm();
        form.setClientName(clientName);
        form.setClientSkuId(clientSkuId);
        String GET_PRODUCT_URL = SERVER_URL + "/product/clientskuandname";
        try {
            ProductData response;
            HttpEntity<ProductForm> requestBody = new HttpEntity<>(form,getHeaders());
            response = restTemplate.exchange(GET_PRODUCT_URL, HttpMethod.POST, requestBody, ProductData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public ProductData getProductData(Long globalSkuId) {
        String GET_PRODUCT_URL = SERVER_URL + "/product/"+ globalSkuId;
        try {
            ProductData response;
            HttpEntity<ProductData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_PRODUCT_URL, HttpMethod.GET, entity, ProductData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }
}
