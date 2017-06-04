package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class XRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("direct:firstAPI")
		.setHeader(Exchange.HTTP_METHOD, simple("GET"))
		.to("http://api.geonames.org/weatherIcao?ICAO=LSZH&username=rheh&style=full")
		.log("${headers}")
		.to("direct:marshalAPI");
		
		from("direct:marshalAPI")
		.marshal().xmljson()
		.to("file:Converted");
	}

}
