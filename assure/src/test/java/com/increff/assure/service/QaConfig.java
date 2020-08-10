package com.increff.assure.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.increff.assure.spring.SpringConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@ComponentScan(//
		basePackages = { "com.increff.assure" }, //
		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = { SpringConfig.class })//
)
@PropertySources({ //
		@PropertySource(value = "classpath:./com/increff/assure/test.properties", ignoreResourceNotFound = true) //
})
public class QaConfig {


}
