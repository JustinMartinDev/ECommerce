/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zeloon.deezer.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpResourceConnection implements ResourceConnection {

    private boolean useProxy;

    private String proxyHost;

    private Integer proxyPort;
    
    // Add by Alain Corbière (15/05/2017)
    public HttpResourceConnection() {
    	this.autoConfigProxy() ;
    }

    public String getData(String url) throws IOException {

        HttpClient client = new DefaultHttpClient();

        if (useProxy()) {
            HttpHost proxy = new HttpHost(proxyHost,proxyPort);
            client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
        }

        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }

    public Boolean useProxy() {
        return useProxy;
    }

    public void useProxy(Boolean useProxy) {
        this.useProxy = useProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }
    
    // Add by Alain Corbière (15/05/2017)
    public void autoConfigProxy() {
		String requestUrl = "http://www.google.com" ;
		try
		{
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.univ-lemans.fr", 3128));
			URL schemaUrl = new URL(requestUrl);
			URLConnection urlConn = schemaUrl.openConnection(proxy);
			urlConn.setConnectTimeout(2000);
			urlConn.setReadTimeout(2000);
			urlConn.setAllowUserInteraction(false);         
			urlConn.setDoOutput(true);
			urlConn.getInputStream() ;
			this.setProxyHost("proxy.univ-lemans.fr") ;
			this.setProxyPort(3128) ;
			this.useProxy(true) ;
		}
		catch (IOException e1) {
			this.useProxy(false) ;
		}
	}

}
