package com.increff.channel.service;


import com.increff.channel.spring.SpringConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(//
		basePackages = { "com.increff.channel" }, //
		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = { SpringConfig.class })//
)
@PropertySources({ //
		@PropertySource(value = "classpath:./com/increff/channel/test.properties", ignoreResourceNotFound = true) //
})
public class QaConfig {


}
