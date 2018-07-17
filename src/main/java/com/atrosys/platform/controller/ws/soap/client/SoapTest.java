package com.atrosys.platform.controller.ws.soap.client;

import soap.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by Kasra on 3/18/2018.
 */
public class SoapTest extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(SoapTest.class);

    public SendTextToFaxResponse getQuote(String ticker) {

        SendTextToFax request = new SendTextToFax();
        request.setFromEmail("k.asgari@live.com");
        request.setBodyText("Testing web service");
        request.setSubject("Test");
        request.setToName("AtroSys");
        request.setFaxNumber("982188749360");

        log.info("Requesting quote for " + ticker);

        SendTextToFaxResponse response = (SendTextToFaxResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.webservicex.net/fax.asmx",
                        request,
                        new SoapActionCallback("http://www.webserviceX.NET/SendTextToFax"));

        return response;
    }
}
