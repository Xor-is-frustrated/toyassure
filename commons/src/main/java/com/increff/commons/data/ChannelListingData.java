package com.increff.commons.data;


import com.increff.commons.form.ChannelListingForm;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChannelListingData extends ChannelListingForm {
    private Long id;
    private String productName;
    private Long globalSkuId;
    
}
