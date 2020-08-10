package com.increff.channel.assure;

import com.increff.channel.service.ApiException;
import com.increff.commons.data.OrderData;
import com.increff.commons.data.OrderItemData;
import com.increff.commons.form.OrderForm;
import com.increff.commons.form.OrderItemFormChannel;
import com.increff.commons.util.AbstractRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class OrderItem extends AbstractRestTemplate {
    @Value("${server.url}")
    private String SERVER_URL;

    public void addOrderItem(OrderItemFormChannel form) throws ApiException{
        String GET_ORDER_URL = SERVER_URL + "/orderitem/channel";

        try {
            HttpEntity<OrderItemFormChannel> requestBody = new HttpEntity<>(form, getHeaders());
            restTemplate.exchange(GET_ORDER_URL, HttpMethod.POST, requestBody, OrderData.class).getBody();
        }catch (Exception e) {
            throw new ApiException(e.getMessage());
        }

    }

    public List<OrderItemData> getitemsByOrderId(Long orderId){
        String GET_ORDERITEM_URL = SERVER_URL + "/orderitem/order/"+orderId;
        try {
            OrderItemData[] response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, OrderItemData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public OrderItemData getOrderItem(Long id){
        String GET_ORDERITEM_URL = SERVER_URL + "/orderitem/"+id;
        try {
            OrderItemData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, OrderItemData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public void updateOrderItem(Long id,OrderItemFormChannel form){
        String GET_ORDERITEM_URL = SERVER_URL + "/orderitem/"+id;
        try {
            OrderItemData response;
            HttpEntity<OrderItemFormChannel> requestBody = new HttpEntity<>(form,getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.PUT, requestBody, OrderItemData.class).getBody();

        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
    }

    public void deleteOrderItem(Long id){
        String GET_ORDERITEM_URL = SERVER_URL + "/orderitem/"+id;
        try {
            OrderItemData response;
            HttpEntity<OrderItemFormChannel> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.DELETE, requestBody, OrderItemData.class).getBody();

        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
    }


    public void getInvoice(Long id, HttpServletResponse servletResponse) {
        String GET_ORDERITEM_URL = SERVER_URL + "/orderitem/fulfill/"+id;
        try {
            OrderItemData response;
            HttpEntity<HttpServletResponse> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, OrderItemData.class).getBody();

        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
    }
}
