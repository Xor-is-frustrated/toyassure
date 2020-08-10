package com.increff.channel.assure;

import com.increff.commons.data.ClientData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import com.increff.commons.util.AbstractRestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Client extends AbstractRestTemplate{
    @Value("${server.url}")
    private String SERVER_URL;

    public List<ClientData> getClientDetails() {
        String GET_CLIENT_URL = SERVER_URL + "/client";
        try {
            ClientData[] response;
            HttpEntity<ClientData[]> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, ClientData[].class).getBody();
            return Arrays.asList(response);
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public ClientData getClientData(Long clientId) {
        String GET_CLIENT_URL = SERVER_URL + "/client/"+clientId;
        try {
            ClientData response;
            HttpEntity<ClientData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, ClientData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

    public ClientData getByName(String clientName) {
        String GET_CLIENT_URL = SERVER_URL + "/client/name/"+clientName;
        try {
            ClientData response;
            HttpEntity<ClientData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CLIENT_URL, HttpMethod.GET, entity, ClientData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }

}
