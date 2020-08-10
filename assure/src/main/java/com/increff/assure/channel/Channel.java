package com.increff.assure.channel;

import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ClientData;
import com.increff.commons.util.AbstractRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.List;

@Component
public class Channel extends AbstractRestTemplate {

    @Value("${server.url}")
    private String SERVER_URL;

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

    public ChannelData getClientData(Long clientId) {
        String GET_CHANNEL_URL = SERVER_URL + "/channel/"+clientId;
        try {
            ChannelData response;
            HttpEntity<ChannelData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CHANNEL_URL, HttpMethod.GET, entity, ChannelData.class).getBody();
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
            HttpEntity<ChannelData> entity = new HttpEntity<>(getHeaders());
            response = restTemplate.exchange(GET_CHANNEL_URL, HttpMethod.GET, entity, ChannelData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            e.getMessage();
        }
        return null;
    }
}
