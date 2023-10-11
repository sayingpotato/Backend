package iampotato.iampotato.domain.order.api;

import iampotato.iampotato.domain.order.application.OrderService;
import iampotato.iampotato.domain.order.domain.Order;
import iampotato.iampotato.domain.order.dto.*;
import iampotato.iampotato.global.util.Result;
import iampotato.iampotato.global.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    @Tag(name = "주문 페이지")
    @Operation(summary = "주문 내역 요청", description = "현재 Customer 의 주문내역을 반환합니다.")
    @GetMapping("api/v1/order/detail")
    public Result<List<OrderDetailResponse>> getOrderDetail(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                            @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<Order> orderDetails = orderService.getOrderDetail(SecurityUtil.getCurrentUserId(), offset, limit);

        List<OrderDetailResponse> responses = orderDetails.stream()
                .map(OrderDetailResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, responses);
    }

    @Tag(name = "점주")
    @Operation(summary = "현재 주문 요청", description = "현재 Owner 가 받은 주문을 반환합니다.")
    @GetMapping("api/v1/order/owner")
    public Result<List<OrderOwnerResponse>> getOrderRequest(OrderOwnerRequest request,
                                                            @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                            @RequestParam(value = "limit", defaultValue = "100") int limit) {

        List<Order> orders = orderService.getOrderRequest(SecurityUtil.getCurrentUserId(), request, offset, limit);

        List<OrderOwnerResponse> responses = orders.stream()
                .map(OrderOwnerResponse::new)
                .collect(Collectors.toList());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, responses);
    }

    @Tag(name = "주문 페이지")
    @Operation(summary = "주문 등록", description = "현재 사용자의 주문을 저장합니다.")
    @PostMapping("api/v1/order")
    public Result<OrderPostResponse> postOrder(@RequestBody OrderPostRequest request) {

        Order order = orderService.postOrder(SecurityUtil.getCurrentUserId(), request);

        OrderPostResponse response = new OrderPostResponse(order);

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }

    @Tag(name = "주문 페이지")
    @Operation(summary = "할인 금액 요청", description = "현재 사용자의 총 할인 금액을 가져옵니다.")
    @GetMapping("api/v1/discount/total")
    public Result<OrderDiscountsResponse> getDisocunts() {

        OrderDiscountsResponse response = orderService.getDiscounts(SecurityUtil.getCurrentUserId());

        return new Result<>(Result.CODE_SUCCESS, Result.MESSAGE_OK, response);
    }
}
