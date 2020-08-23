package com.order.management.orderservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import com.order.management.orderservice.pojo.OrderServicePOJO;

public interface OrderService {
	
	public boolean saveOrder(OrderServicePOJO orderServicePOJO) throws ParseException, ClientProtocolException, IOException;
	public List<OrderServicePOJO> readAllOrders();

}
