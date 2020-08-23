package com.order.management.orderservice.utility;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.order.management.orderservice.model.Order;
import com.order.management.orderservice.pojo.OrderServicePOJO;

public class OrderUtility {

	public static Order orderconverPOJOtoModel(OrderServicePOJO orderServicePOJO, Map<String, String> orderMap)
			throws ParseException {
		Order order = new Order();
		order.setCustomername(orderServicePOJO.getCustomerName());
		StringBuffer sf = new StringBuffer();
		int i = 0;
		String key = "";
		String value = "";
		if (orderServicePOJO.getOrderItems().size() > 0) {
			for (String productItem : orderServicePOJO.getOrderItems()) {
				if (i == 0) {
					String orderProd[] = productItem.split(" ");
					key = orderProd[0] + "-" + orderProd[1];
					sf.append(orderProd[0] + "-" + orderProd[1]+",");

					orderMap.put(key, "");
					
				}
				if (i == 1) {
					String orderProd[] = productItem.split(" ");

					if (orderMap.containsKey(key)) {
						value = orderProd[0] + "-" + orderProd[1];
						sf.append(orderProd[0] + "-" + orderProd[1]+",");
						orderMap.put(key, value);
						//i = 0;

					}
				}
				if(i==0) {
					i=i+1;
				}else {
					i=0;
				}

			}
		}
		String orderItems = sf.toString().substring(0, sf.toString().length() - 1);
		order.setOrderitems(orderItems);
		Date date = convertStringToDate(orderServicePOJO.getOrderDate());
		order.setOrderdate(date);
		order.setShippingaddress(orderServicePOJO.getShippingAddress());
		order.setTotal(orderServicePOJO.getTotal());
		
		return order;
	}

	private static Date convertStringToDate(String orderDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		java.util.Date parsed = format.parse(orderDate);
		java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
		return sqlDate;
	}

	public static List<OrderServicePOJO> convertModelToPJO(List<Order> order) {

		List<OrderServicePOJO> orderPOJOList = new ArrayList<>();
		
		for (Order order1 : order) {
			OrderServicePOJO orderServicePOJO = new OrderServicePOJO();
			orderServicePOJO.setCustomerName(order1.getCustomername());
			orderServicePOJO.setOrderDate(order1.getOrderdate().toString());
			if(!order1.getOrderitems().isEmpty()) {
				List<String> orderItemList = new ArrayList();
			String orderItem[] = order1.getOrderitems().split(",");
				for(String str : orderItem)
				{
					orderItemList.add(str);
				}
				orderServicePOJO.setOrderItems(orderItemList);
			}
			orderServicePOJO.setShippingAddress(order1.getShippingaddress());
			orderServicePOJO.setTotal(order1.getTotal());
			orderPOJOList.add(orderServicePOJO);
		}

		return orderPOJOList;
	}

}
