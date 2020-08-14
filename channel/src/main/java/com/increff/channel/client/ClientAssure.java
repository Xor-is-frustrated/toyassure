package com.increff.channel.client;

import com.increff.channel.service.ApiException;

import com.increff.commons.data.*;
import com.increff.commons.form.*;
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
public class ClientAssure extends AbstractRestTemplate {
//    @Value("${server.url}")
    private String SERVER_URL="http://localhost:9000/assure/api";

    public ChannelData selectChannel(Long id) {
        String GET_CLIENT_URL = SERVER_URL + "/channel/"+ id;
        try {
            ChannelData response;
            HttpEntity<ChannelData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, ChannelData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }



    public ChannelData getByName(String channelName) {
        String GET_CHANNEL_URL = SERVER_URL + "/channel/name/"+channelName;
        try {
            ChannelData response;
            HttpEntity<ChannelForm> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CHANNEL_URL, HttpMethod.GET, entity, ChannelData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public ProductData getProductByClientNameAndClientSkuId(String clientSkuId, String clientName) {
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
            return null;
        }

    }

    public ProductData getProduct(Long globalSkuId) {
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

    public List<PartyData> getAllClients() {
        String GET_CLIENT_URL = SERVER_URL + "/client";
        try {
            PartyData[] response;
            HttpEntity<PartyData[]> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, PartyData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public List<PartyData> getAllClientsType() {
        String GET_CLIENT_URL = SERVER_URL + "/client/clients";
        try {
            PartyData[] response;
            HttpEntity<PartyData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, PartyData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public List<PartyData> getAllCustomersType() {
        String GET_CLIENT_URL = SERVER_URL + "/client/customers";
        try {
            PartyData[] response;
            HttpEntity<PartyData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, PartyData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public PartyData getClient(Long clientId) {
        String GET_CLIENT_URL = SERVER_URL + "/client/"+clientId;
        try {
            PartyData response;
            HttpEntity<PartyData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, PartyData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public PartyData getClientByName(String clientName) {
        String GET_CLIENT_URL = SERVER_URL + "/client/name/"+clientName;
        try {
            PartyData response;
            HttpEntity<PartyData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, PartyData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }



    public OrderData addOrder(OrderForm form)throws ApiException {
        String GET_ORDER_URL = SERVER_URL + "/order";
        try {
            OrderData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(form,getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.POST, requestBody, OrderData.class).getBody();
            return response;
        } catch (Exception e) {
           return null;
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

    public OrderData getOrderByOrderId(String channelOrderId){
        String GET_ORDER_URL = SERVER_URL + "/order/name/"+channelOrderId;
        try {
            OrderData response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.GET, requestBody, OrderData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            return null;
        }

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

    public OrderItemData addOrderItem(OrderItemFormChannel form) throws ApiException{
        String GET_ORDER_URL = SERVER_URL + "/orderitem/channel";

        try {
            OrderItemData response;
            HttpEntity<OrderItemFormChannel> requestBody = new HttpEntity<>(form, getHeaders());
            response=restTemplate.exchange(GET_ORDER_URL, HttpMethod.POST, requestBody, OrderItemData.class).getBody();
            return response;

        }catch (Exception e) {
            return null;
        }

    }

    public List<OrderItemData> getItemsByOrderId(Long orderId){
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

    public List<ChannelData> getAllChannels() {
        String GET_ORDERITEM_URL = SERVER_URL + "/channel";
        try {
            ChannelData[] response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, ChannelData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;

    }

    public List<PartyData> getOnlyClients() {
        String GET_ORDERITEM_URL = SERVER_URL + "/client/clients";
        try {
            PartyData[] response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, PartyData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;

    }

    public List<PartyData> getOnlyCusotmers() {
        String GET_ORDERITEM_URL = SERVER_URL + "/client/customers";
        try {
            PartyData[] response;
            HttpEntity<OrderForm> requestBody = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_ORDERITEM_URL, HttpMethod.GET, requestBody, PartyData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;

    }

    public List<OrderData> getOrderBySearch(OrderSearchForm form) {

        String GET_ORDER_URL = SERVER_URL + "/order/searchexternal";
        try {
            OrderData[] response;
            HttpEntity<OrderSearchForm> requestBody = new HttpEntity<>(form,getHeaders());
            response = restTemplate.exchange(GET_ORDER_URL, HttpMethod.POST, requestBody, OrderData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }
}
