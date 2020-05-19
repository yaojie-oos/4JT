package com.jt;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.utils.HttpClientService;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHttpClient {
	@Autowired
	private CloseableHttpClient client;
	
	@Autowired
	private HttpClientService service;
	@Test
	public void testHttpClient() throws ClientProtocolException, IOException {
		String url = "http://www.baidu.com";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);
		if(response.getStatusLine().getStatusCode()==200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
		}
	}
	
	@Test
	public void testHttpService() {
		String url = "http://www.baidu.com";
		String result = service.doGet(url);
		System.out.println(result);
	}
	
	
}
