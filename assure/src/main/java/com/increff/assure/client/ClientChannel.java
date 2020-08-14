package com.increff.assure.client;

import com.increff.commons.data.ChannelData;
import com.increff.commons.data.ChannelListingData;
import com.increff.commons.form.ChannelListingForm;
import com.increff.commons.util.AbstractRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

@Component
public class ClientChannel extends AbstractRestTemplate {

    @Value("${server.url}")

    private String SERVER_URL;

    public ChannelData getByName(String channelName) {
        String GET_CHANNEL_URL = SERVER_URL + "/channel/"+channelName;
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

    public ChannelListingData getByChannelSkuIdAndChannel(String channelSkuId, Long channelId) {
        ChannelListingForm form= new ChannelListingForm();
        form.setChannelSkuId(channelSkuId);
        form.setChannelId(channelId);

        String GET_CHANNELLISTING_URL = SERVER_URL + "/channellisting/channelidandsku";
        try {
            ChannelListingData response;
            HttpEntity<ChannelListingForm> entity = new HttpEntity<>(form,getHeaders());

            response = restTemplate.exchange(GET_CHANNELLISTING_URL, HttpMethod.POST, entity, ChannelListingData.class).getBody();
            return response;
        } catch (HttpStatusCodeException e) {
            return null;
        }

    }


}
