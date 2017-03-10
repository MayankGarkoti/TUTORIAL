package com.mayank.tutorial.camel.timer;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTimerSimpleExample {
	public static void main(String[] args) throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("timer://simpleTimer?period=1000")
							.setBody(simple("Hello from timer at ${header.firedTime}"))
							.to("stream:out");
				}
			});
			camelContext.start();
			Thread.sleep(3000);
		} finally {
			camelContext.stop();
		}
	}
}
