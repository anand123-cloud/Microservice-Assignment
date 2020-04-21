package com.demo.foodservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.foodservice.dto.AccountDetail;
import com.demo.foodservice.dto.OrderDetail;
import com.demo.foodservice.dto.OrderItemDto;
import com.demo.foodservice.dto.OrderRequestDto;
import com.demo.foodservice.dto.OrderResponse;
import com.demo.foodservice.dto.PaymentRequest;
import com.demo.foodservice.entity.OrderItem;
import com.demo.foodservice.entity.User;
import com.demo.foodservice.entity.UserOrder;
import com.demo.foodservice.feign.BankingServiceClient;
import com.demo.foodservice.repository.OrderRepository;
import com.demo.foodservice.repository.UserRepository;

@Service
public class  OrderServiceImpl  implements OrderService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BankingServiceClient bankingServiceClient;

	@Override
	public OrderResponse createOrder(OrderRequestDto orderRequestDto, String userId, String vendor) {
		
		OrderResponse orderResponse=null;
		
		if(userRepository.findById(userId).isPresent()) {
			User user = userRepository.findById(userId).get();
			List<OrderItemDto> orderRequestDtos= orderRequestDto.getOrderItemRequests();
			if(!orderRequestDtos.isEmpty()) {
				
				UserOrder userOrder=new UserOrder();
				userOrder.setOrderCreated(new Date());
				userOrder.setUser(user);
				List<OrderItem> orderItems=createOrderItemsFromOrderRequest(orderRequestDtos,userOrder);
				
		        BigDecimal totalPrice = orderItems.stream()
		                .map(item -> {
		                	BigDecimal quantity= BigDecimal.valueOf(item.getQuantity());
		                	return  quantity.multiply(item.getPrice());
		                	})
		                .reduce(BigDecimal.ZERO, BigDecimal::add);
				userOrder.setTotalPrice(totalPrice);
				userOrder.setQuantity(orderItems.size());
				userOrder.setOrderItems(orderItems);
				userOrder.setOrderId(generateUniqueOrderId());
				String paymentStatus = makePaymentForOrder(userId,totalPrice);
				userOrder.setPaymentId(paymentStatus);
				UserOrder order = orderRepository.save(userOrder);
				 orderResponse=createOrderDetail(order);
			} else {
				
				throw new RuntimeException("user must select one or more menu item");
			}
			
		} else  {
			throw new RuntimeException("user not found");
		}
		return orderResponse;
	}

	private OrderResponse createOrderDetail(UserOrder order) {
		
		List<OrderItem> orderItems = order.getOrderItems();
		List<OrderItemDto> orderItemsToDtos = convertOrderItemsToDtos(orderItems);
		OrderResponse orderResponse=new OrderResponse();
		Map<OrderDetail, List<OrderItemDto>> orderResposeMap=new HashMap<>();
		orderResposeMap.put(mapOrderToOrderDetail(order), orderItemsToDtos);
		orderResponse.setMap(orderResposeMap);
		return orderResponse;
	}

	private OrderDetail mapOrderToOrderDetail(UserOrder order) {
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderId(order.getOrderId());
		orderDetail.setQuantity(order.getQuantity());
		orderDetail.setTotalPrice(order.getTotalPrice());
		orderDetail.setPaymentId(order.getPaymentId());
		orderDetail.setOrderCreated(order.getOrderCreated());
		return orderDetail;
	}

	private String makePaymentForOrder(String userId, BigDecimal totalPrice) {
		
		AccountDetail accountDetails = bankingServiceClient.getAccountDetails(userId);
		PaymentRequest paymentRequest=new PaymentRequest();
		paymentRequest.setAmount(totalPrice);
		paymentRequest.setCardNumber(accountDetails.getCardNumber());
		paymentRequest.setCvv(accountDetails.getCvv());
		paymentRequest.setExpirationDate(accountDetails.getExpirationDate());
		return bankingServiceClient.makePayment(paymentRequest, userId);
		
	}

	private List<OrderItem> createOrderItemsFromOrderRequest(List<OrderItemDto> orderRequestDtos, UserOrder userOrder) {
		List<OrderItem> orderItems
		=orderRequestDtos.stream()
		.map(orderRequest -> {
			OrderItem orderItem=new OrderItem();
			orderItem.setItemName(orderRequest.getName());
			orderItem.setOrderItemId(generateUniqueOrderItemId());
			orderItem.setPrice(orderRequest.getPrice());
			orderItem.setQuantity(orderRequest.getQuantity());
			orderItem.setUserOrder(userOrder);
			return orderItem;
			}).collect(Collectors.toList());
		return orderItems;
	}

	private String generateUniqueOrderItemId() {
		int unique = 100 + new Random().nextInt(900);	
		String orderItemId="item"+unique;
		return orderItemId;
	}
	
	private String generateUniqueOrderId() {
		int unique = 1000 + new Random().nextInt(9000);	
		String orderId="order"+unique;
		return orderId;
	}

	@Override
	public OrderResponse getOrderHistory(String userId) {
		OrderResponse orderResponse=new OrderResponse();
		if(userRepository.findById(userId).isPresent()) {
			User user = userRepository.findById(userId).get();
			List<UserOrder> orders = user.getUserOrders().subList(0, 2);
			
			Map<OrderDetail, List<OrderItemDto>> orderResposeMap=createOrderResposeMap(orders);
			orderResponse.setMap(orderResposeMap);
		} 
		return orderResponse;
	}

//	private Map<String, List<OrderItemDto>> createOrderResposeMap(List<UserOrder> orders) {
//		
//		Map<String, List<OrderItemDto>> orderResposeMap=new HashMap<>();
//		
//		orders.stream().forEach(
//				order -> {
//					List<OrderItem> orderItems = order.getOrderItems();
//					List<OrderItemDto> orderItemsDtos= convertOrderItemsToDtos(orderItems);
//					orderResposeMap.put(order.getOrderId(), orderItemsDtos);
//					});
//		
//		return orderResposeMap;
//	}
	
	
	
	
private Map<OrderDetail, List<OrderItemDto>> createOrderResposeMap(List<UserOrder> orders) {
		
		Map<OrderDetail, List<OrderItemDto>> orderResposeMap=new HashMap<>();
		
		orders.stream().forEach(
				order -> {
					List<OrderItem> orderItems = order.getOrderItems();
					List<OrderItemDto> orderItemsDtos= convertOrderItemsToDtos(orderItems);
					OrderDetail orderDetail=new OrderDetail();
					orderDetail.setOrderId(order.getOrderId());
					orderDetail.setQuantity(order.getQuantity());
					orderDetail.setTotalPrice(order.getTotalPrice());
					orderDetail.setPaymentId(order.getPaymentId());
					orderDetail.setOrderCreated(order.getOrderCreated());
					orderResposeMap.put(orderDetail, orderItemsDtos);
					});
		
		return orderResposeMap;
	}
	
	

	private List<OrderItemDto> convertOrderItemsToDtos(List<OrderItem> orderItems) {
		 List<OrderItemDto> orderItemsDtos=null;
		if(orderItems.isEmpty()) {
			orderItemsDtos=new ArrayList<>();
		} else {
			orderItemsDtos=orderItems.stream().map(orderItem ->{ 
				OrderItemDto orderItemDto=new OrderItemDto();
				orderItemDto.setName(orderItem.getItemName());
				orderItemDto.setQuantity(orderItem.getQuantity());
				orderItemDto.setPrice(orderItem.getPrice());
				return orderItemDto;
			}).collect(Collectors.toList());
		}
		
		return orderItemsDtos;
	}
	

}
