package ku.cs.transport_application.controller;

import ku.cs.transport_application.DTO.OrderDTO;
import ku.cs.transport_application.common.OrderStatus;
import ku.cs.transport_application.common.TransportationWorkerStatus;
import ku.cs.transport_application.service.FileService;
import ku.cs.transport_application.service.MailSenderService;
import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @GetMapping("/orders/unpaid-orders")
    public ResponseEntity<List<OrderDTO>> getUnpaidOrder() {
        return ResponseEntity.ok(orderService.getUnpaidOrder());
    }

    @GetMapping("/orders/uncheck-orders")
    public ResponseEntity<List<OrderDTO>> getUncheckOrder() {
        return ResponseEntity.ok(orderService.getUncheckOrder());
    }

    @GetMapping("/orders/check-orders")
    public ResponseEntity<List<OrderDTO>> getCheckedOrder() {
        return ResponseEntity.ok(orderService.getCheckedOrder());
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrder(@PathVariable("userId") UUID userID) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userID));
    }

    @GetMapping("/orders/worker/{transportationWorkerId}")
    public ResponseEntity<List<OrderDTO>> getTransportationWorkerOrder(@PathVariable("transportationWorkerId") UUID transportationWorkerId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(transportationWorkerId));
    }

    @GetMapping("/orders/not-complete-orders")
    public ResponseEntity<?> getCompleteOrder() {
        return ResponseEntity.ok(orderService.getNotCompleteOrder());
    }

    @GetMapping("/orders/all-orders")
    public ResponseEntity <List<OrderDTO>> getAllOrder () {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/orders/delivered-orders")
    public ResponseEntity <List<OrderDTO>> getDelivered() {
        return ResponseEntity.ok(orderService.getDelivered());
    }

    @GetMapping("/orders/on-going-orders")
    public ResponseEntity <List<OrderDTO>> getOnGoingOrder() {
        return ResponseEntity.ok(orderService.getOnGoing());
    }

    @GetMapping("/orders/uploaded-orders")
    public ResponseEntity <List<OrderDTO>> getUploadedOrder() {
        return ResponseEntity.ok(orderService.getUploaded());
    }

    @GetMapping("/orders/completed-orders")
    public ResponseEntity <List<OrderDTO>> getCompletedOrder() {
        return ResponseEntity.ok(orderService.getComplete());
    }

    @GetMapping("/orders/search")
    public ResponseEntity <List<OrderDTO>> searchOrder(@RequestParam String keyword) {
        List<OrderDTO> orders = orderService.searchOrders(keyword);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/change-order-worker-status")
    public ResponseEntity<?> changeOrderAndWorkerStatus(@RequestParam("orderId") UUID orderId,
                                               @RequestParam("workerId") UUID workerId,
                                               @RequestParam("status") OrderStatus status,
                                               @RequestParam("workerStatus") TransportationWorkerStatus workerStatus) {
        orderService.upDateOrderStatus(orderId, status);
        transportationWorkerService.updateTransportationWorker(workerId, workerStatus);
        mailSenderService.sendEmail(orderId);
        return ResponseEntity.ok(Map.of("message", "Order status updated successfully"));
    }

    @PostMapping("/orders/order-detail/{orderId}/change-status")
    public ResponseEntity<?> changeOrderStatus(@PathVariable("orderId") UUID orderId,
                                                        @RequestParam("status") OrderStatus status) {
        orderService.upDateOrderStatus(orderId, status);
        mailSenderService.sendEmail(orderId);
        return new ResponseEntity<>(Map.of("message", "Order status updated successfully"), HttpStatus.OK);
    }

    @GetMapping("/orders/order-detail/{orderId}")
    public ResponseEntity<?> getOrderDetailById(@PathVariable("orderId") UUID orderId) {
        return ResponseEntity.ok(orderService.getOrderDetail(orderId));
    }

    @GetMapping("/orders/order-detail/{orderId}/shipment-doc")
    public ResponseEntity<Resource> viewShipmentDoc(@PathVariable UUID orderId) {
        Resource file = fileService.getShipmentDoc(orderId);
        return ResponseEntity.ok()
                .header("X-Frame-Options", "ALLOW-FROM http://localhost:5173")
                .header("Content-Security-Policy", "frame-ancestors 'self' http://localhost:5173")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }
}
