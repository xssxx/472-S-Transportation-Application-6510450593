package ku.cs.transport_application.service.payment;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.repository.HistoryRepository;
import ku.cs.transport_application.request.CreateHistoryRequest;
import ku.cs.transport_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserService userService;

    /*
     * @desc    get all histories
     * @return  list of History entity
     */
    public List<History> getHistories() {
        return historyRepository.findAll();
    }

    /*
     * @desc    get histories by user id
     * @param   id of user to find histories
     * @return  list of History entity
     */
    public List<History> getHistoriesByUserId(UUID userId) {
        return historyRepository.findByUserId(userId);
    }

    /*
     * @desc    add history by user id
     * @param   id of user to find histories
     * @return  result of saving (success ? object : null)
     */
    public History addHistoryByUserId(CreateHistoryRequest r) {
        History history = History.builder()
                .method(r.getMethod())
                .amount(r.getAmount())
                .paymentDate(LocalDateTime.now())
                .user(userService.findById(r.getUserId()))
                .build();

        return historyRepository.save(history);
    }
}

