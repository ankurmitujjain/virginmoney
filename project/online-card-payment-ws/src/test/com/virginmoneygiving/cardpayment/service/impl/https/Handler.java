package com.virginmoneygiving.cardpayment.service.impl.https;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * A URL Stream Handler impl to create a mock HttpsUrlConnection.
 * 
 * @author Robin Bramley, Opsera Ltd.
 */
public class Handler extends URLStreamHandler {

    /** {@inheritDoc}*/
    @Override
    protected URLConnection openConnection(URL url) throws IOException {
        return new MockHttpsUrlConnectionImpl(url, url.getPath().contains("success"));
    }
}
