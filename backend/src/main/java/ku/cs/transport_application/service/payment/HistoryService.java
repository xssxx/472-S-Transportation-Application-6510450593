package ku.cs.transport_application.service.payment;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.repository.HistoryRepository;
import ku.cs.transport_application.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final OrderService orderService;

    /*
     * @desc    get all histories
     * @return  list of History entity
     */
    public List<History> getHistories() {
        try {
            return historyRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /*
     * @desc    get histories by user id
     * @param   id of user to find histories
     * @return  list of History entity
     */
    public List<History> getHistoriesByUserId(UUID userId) {
        try {
            return historyRepository.findByUserId(userId);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }

    /*
     * @desc    add history by order id
     * @param   create history request (order id)
     * @return  result of saving (success ? object : null)
     */
    public History addHistoryByOrderId(UUID orderId) {
        try {
            Order order = orderService.getOrdersByOrderId(orderId);
            History history = new History();
            history.setAmount(order.getTotal());
            history.setPaymentDate(LocalDateTime.now());
            history.setOrder(order);

            return historyRepository.save(history);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return null;
    }
}

