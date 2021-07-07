package com.syrisa.springlibrarydocker.controller;

import com.syrisa.springlibrarydocker.dto.OrdersDto;
import com.syrisa.springlibrarydocker.model.impl.Orders;
import com.syrisa.springlibrarydocker.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> create(@RequestBody OrdersDto ordersDto) {
        try {
            OrdersDto editedOrder = orderService.create(ordersDto.toOrders()).toOrdersDto();
            URI location = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/v1/order")
                    .path("{ordersId}")
                    .buildAndExpand(editedOrder.toOrders().getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    /*  @PostMapping
      @ResponseStatus(HttpStatus.CREATED)
      public OrdersDto create(@RequestBody OrdersDto ordersDto) {
          try {
              return orderService.create(ordersDto.toOrders()).toOrdersDto();
          } catch (Exception exception) {
              throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
          }
      }
  */
    @PutMapping
    public OrdersDto update(@RequestBody OrdersDto ordersDto) {
        try {
            return orderService.update(ordersDto.toOrders()).toOrdersDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping("/{ordersId}")
    public OrdersDto getOrderByOrdersId(@PathVariable("ordersId") int ordersId) {
        try {
            return orderService.getOrdersByOrdersId(ordersId).toOrdersDto();
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }

    @GetMapping(value = "/orders", params = {"page", "size"})
    public List<OrdersDto> getOrders(@Min(0) int page, @Min(1) int size) {
        return orderService.getAll(PageRequest.of(page, size))
                .stream()
                .map(Orders::toOrdersDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/undo/{ordersId}")
    public String delete(@PathVariable("ordersId") long ordersId) {
        try {
            return orderService.delete(ordersId);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        }
    }
}
