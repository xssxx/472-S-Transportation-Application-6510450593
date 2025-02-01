package ku.cs.transport_application.controller;


import ku.cs.transport_application.DTO.TransportationWorkerDTO;
import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.TransportationWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transportation-workers")
public class TransportationWorkerController {

    @Autowired
    private TransportationWorkerService transportationWorkerService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<TransportationWorkerDTO>> getTransportationWorkers() {
        List<TransportationWorkerDTO> workerDTO = transportationWorkerService.getAvailableTransportationWorker();
        return ResponseEntity.ok(workerDTO);
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<?> getTransportationWorkerDetails(@PathVariable UUID workerId) {
        return ResponseEntity.ok(orderService.getOrdersByWorker(workerId));
    }

    @PostMapping("/worker/worker-detail/{workerId}/add-order")
    public ResponseEntity<?> assignWorkerToOrder(@RequestParam("orderId") UUID orderId, @PathVariable("workerId") UUID workerId) {
        try {
            orderService.upDateOrderToWorker(workerId, orderId);
            return new ResponseEntity<>(Map.of("message", "Worker assigned to order successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to assign worker to order"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
