package ku.cs.transport_application.controller;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.request.CreateHistoryRequest;
import ku.cs.transport_application.service.payment.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final GlobalExceptionHandler exceptionHandler;

    /*
     * [TODO] Error handling *
     */

    @GetMapping("/payments")
    public ResponseEntity<List<History>> getPayments() {
        return ResponseEntity.ok(historyService.getHistories());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<List<History>> getPayment(@PathVariable UUID id) {
        return ResponseEntity.ok(historyService.getHistoriesByUserId(id));
    }

    @PostMapping("/payments")
    public ResponseEntity<History> addPayment(@RequestBody CreateHistoryRequest request) {
        return ResponseEntity.ok(historyService.addHistoryByUserId(request));
    }
}
