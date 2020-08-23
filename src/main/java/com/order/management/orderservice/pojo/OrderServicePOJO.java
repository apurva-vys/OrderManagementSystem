package com.order.management.orderservice.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "customer name", "order date", "shipping address", "order items", "total" })
public class OrderServicePOJO {

	@JsonProperty("customer name")
	private String customerName;
	@JsonProperty("order date")
	private String orderDate;
	@JsonProperty("shipping address")
	private String shippingAddress;
	@JsonProperty("order items")
	private List<String> orderItems = new ArrayList<String>();
	@JsonProperty("total")
	private String total;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("customer name")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("customer name")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public OrderServicePOJO withCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	@JsonProperty("order date")
	public String getOrderDate() {
		return orderDate;
	}

	@JsonProperty("order date")
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public OrderServicePOJO withOrderDate(String orderDate) {
		this.orderDate = orderDate;
		return this;
	}

	@JsonProperty("shipping address")
	public String getShippingAddress() {
		return shippingAddress;
	}

	@JsonProperty("shipping address")
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public OrderServicePOJO withShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}

	@JsonProperty("order items")
	public List<String> getOrderItems() {
		return orderItems;
	}

	@JsonProperty("order items")
	public void setOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderServicePOJO withOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
		return this;
	}

	@JsonProperty("total")
	public String getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(String total) {
		this.total = total;
	}

	public OrderServicePOJO withTotal(String total) {
		this.total = total;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public OrderServicePOJO withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(customerName).append(orderDate).append(shippingAddress).append(orderItems)
				.append(total).append(additionalProperties).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof OrderServicePOJO) == false) {
			return false;
		}
		OrderServicePOJO rhs = ((OrderServicePOJO) other);
		return new EqualsBuilder().append(customerName, rhs.customerName).append(orderDate, rhs.orderDate)
				.append(shippingAddress, rhs.shippingAddress).append(orderItems, rhs.orderItems)
				.append(total, rhs.total).append(additionalProperties, rhs.additionalProperties).isEquals();
	}

}
