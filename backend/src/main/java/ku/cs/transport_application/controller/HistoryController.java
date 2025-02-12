package ku.cs.transport_application.controller;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.request.AddHistoryRequest;
import ku.cs.transport_application.service.payment.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    /*
     * TODO: response DTO
     */

    @GetMapping("/payments")
    public ResponseEntity<List<History>> getPayments() {
        List<History> histories = historyService.getHistories();

        if (histories.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(histories);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<List<History>> getPayment(@PathVariable UUID id) {
        List<History> histories = historyService.getHistoriesByUserId(id);

        if (histories.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(histories);
    }

    @PostMapping("/payments")
    public ResponseEntity<History> addPayment(@RequestBody AddHistoryRequest r) {
        History createdHistory = historyService.addHistoryByOrderId(r.getOrderId());

        if (createdHistory == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdHistory);
    }
}
