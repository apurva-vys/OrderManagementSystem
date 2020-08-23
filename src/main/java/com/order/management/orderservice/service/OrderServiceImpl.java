package com.order.management.orderservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.management.orderservice.exception.OrderNotfoundException;
import com.order.management.orderservice.exception.OrderQuantityNotPresentException;
import com.order.management.orderservice.model.Order;
import com.order.management.orderservice.model.OrderRepository;
import com.order.management.orderservice.pojo.OrderServicePOJO;
import com.order.management.orderservice.utility.OrderUtility;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	private static final String serviceURL = "http://localhost:8081/orderitems/items/readproducts?";
	static Map<String, String> orderProductMap = new LinkedHashMap<>();

	@Override
	public boolean saveOrder(OrderServicePOJO orderServicePOJO)
			throws ParseException, ClientProtocolException, IOException {

		Order order = OrderUtility.orderconverPOJOtoModel(orderServicePOJO, orderProductMap);
		boolean flag = validateQuantity(orderServicePOJO);
		if(flag) {
			Order order1 = orderRepository.save(order);
			return true;
		}else {
			throw new OrderQuantityNotPresentException();
		}
			
		

		
	}

	private boolean validateQuantity(OrderServicePOJO orderServicePOJO) throws ClientProtocolException, IOException {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(serviceURL);
		getRequest.addHeader("accept", "application/json");
		if (orderProductMap.size() > 0) {
			for (Map.Entry<String, String> mMap : orderProductMap.entrySet()) {
				String orderName[] = mMap.getKey().split("-");
				List<NameValuePair> urlParameters = new ArrayList<>();				
				getRequest.getParams().setParameter("name", orderName[1]);
				HttpResponse res = httpClient.execute(getRequest);
				HttpEntity entity = res.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity);
					
				}

			}
			return true;
		}else {
			throw new OrderNotfoundException();
		}

		
	}

	@Override
	public List<OrderServicePOJO> readAllOrders() {
		Iterable<Order> iterable = orderRepository.findAll();

		List<Order> orderList = IteratorUtils.toList(iterable.iterator());
		List<OrderServicePOJO> orderServicePOJO = OrderUtility.convertModelToPJO(orderList);
		return orderServicePOJO;
	}

}
