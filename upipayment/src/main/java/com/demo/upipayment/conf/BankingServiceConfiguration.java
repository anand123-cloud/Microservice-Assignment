package com.demo.upipayment.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class BankingServiceConfiguration {

	
	   @Autowired
		IClientConfig ribbonClientConfig;
		
		@Bean
		public IRule ribbonRule(IClientConfig ribbonClientConfig ) {
			return new AvailabilityFilteringRule();
		}

		@Bean
		public IPing ribbonPing() {
			return new PingUrl();
		}


}
