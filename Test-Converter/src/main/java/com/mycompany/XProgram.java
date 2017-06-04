package com.mycompany;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.util.ExchangeHelper;

public class XProgram {

	public static void main(String[] args) throws Exception{
		
		CamelContext con = new DefaultCamelContext();
		con.addRoutes(new XRoute());
		
		con.start();
        Thread.sleep(5000);
        
		ProducerTemplate template = con.createProducerTemplate();

		Object Api1 = template.requestBody("direct:firstAPI", null, String.class);
		Exchange exchange = new DefaultExchange(con);
		String response1 = ExchangeHelper.convertToType(exchange, String.class, Api1);
		
		System.out.println("BODDY: " + response1);
		
		//Shorter way:
		
//		String xmlToJsonAPIEndpoint = "direct:firstAPI";
//		template.requestBody(xmlToJsonAPIEndpoint, null, String.class);
		
		con.stop();
	}

}
