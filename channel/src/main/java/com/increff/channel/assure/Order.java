package com.increff.channel.assure;

import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.data.ProductData;
import com.increff.commons.form.OrderForm;
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
public class Order extends AbstractRestTemplate {

    @Value("${server.url}")
    private String SERVER_URL;

    public OrderData addOrder(OrderForm form)throws ApiException{
        String GET_ORDER_URL = SERVER_URL + "/order";
        try {
            OrderData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(form,getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.POST, requestBody, OrderData.class).getBody();
            return response;
        } catch (Exception e) {
           throw new ApiException(e.getMessage());
        }
    }

    public OrderData getOrder(Long id){
        String GET_ORDER_URL = SERVER_URL + "/order/"+id;
        try {
            OrderData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.GET, requestBody, OrderData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();

        }
        return null;
    }

    public void deleteOrder(Long id){
        String GET_ORDER_URL = SERVER_URL + "/order/"+id;
        try {
            OrderData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.DELETE, requestBody, OrderData.class).getBody();

        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
    }

    public List<OrderData> getAllChannelOrders(){
        String GET_ORDER_URL = SERVER_URL + "/order/external";
        try {
            OrderData[] response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.GET, requestBody, OrderData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

}
