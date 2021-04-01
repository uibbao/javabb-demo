package cn.javabb.seata.controller;

import cn.javabb.seata.dto.PlaceOrderRequest;
import cn.javabb.seata.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/03/30 17:53
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public String placeOrder(@Validated @RequestBody PlaceOrderRequest request) {
        orderService.placeOrder(request);
        return "下单成功";
    }

    @PostMapping("/test1")
    @ApiOperation("测试商品库存不足-异常回滚")
    public String test1() {
        // 商品单价10元，库存20个,用户余额50元，模拟一次性购买22个。 期望异常回滚
        orderService.placeOrder(new PlaceOrderRequest(1, 1, 22));
        return "下单成功";
    }

    @PostMapping("/test2")
    @ApiOperation("测试用户账户余额不足-异常回滚")
    public String test2() {
        // 商品单价10元，库存20个，用户余额50元，模拟一次性购买6个。 期望异常回滚
        orderService.placeOrder(new PlaceOrderRequest(1, 1, 6));
        return "下单成功";
    }
}
