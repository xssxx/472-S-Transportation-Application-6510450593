package ku.cs.transport_application.controller;

import ku.cs.transport_application.entity.Receipt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public class ReceiptController {
    @GetMapping("/receipt/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable UUID id) {
        return null;
    }


}
