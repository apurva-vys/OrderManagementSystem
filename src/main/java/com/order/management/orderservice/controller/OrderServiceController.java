package com.order.management.orderservice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.management.orderservice.exception.OrderNotProcessException;
import com.order.management.orderservice.exception.OrderNotfoundException;
import com.order.management.orderservice.pojo.OrderServicePOJO;
import com.order.management.orderservice.service.OrderServiceImpl;

@RestController
@RequestMapping("/ordermanagement")
public class OrderServiceController {

	@Autowired
	OrderServiceImpl OrderServiceImpl;

	@RequestMapping(value = "/orderservice/save", method = RequestMethod.POST)
	public ResponseEntity<Object> generateOrder(@RequestBody OrderServicePOJO orderServicePOJO) throws ParseException, ClientProtocolException, IOException {

		boolean flag = OrderServiceImpl.saveOrder(orderServicePOJO);
		if (flag) {
			return new ResponseEntity<>("Order are process successfully", HttpStatus.OK);
		} else {
			throw new OrderNotProcessException();
		}
		

	}

	@RequestMapping(value = "/orderservice/getAllOrders", method = RequestMethod.GET)
	public @ResponseBody List<OrderServicePOJO> getAllOrders() throws ParseException {

		List<OrderServicePOJO> OrderServicePOJOList = OrderServiceImpl.readAllOrders();

		if(OrderServicePOJOList.size() > 0) {
			return OrderServicePOJOList;
		}else {
			throw new OrderNotfoundException();
		}
		

	}

}
