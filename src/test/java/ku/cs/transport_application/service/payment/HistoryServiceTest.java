package ku.cs.transport_application.service.payment;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.entity.Order;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.repository.HistoryRepository;
import ku.cs.transport_application.response.HistoryResponse;
import ku.cs.transport_application.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private HistoryService historyService;

    private UUID orderId;
    private Order order;
    private History history;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare test data
        user = new User();
        user.setId(UUID.randomUUID());

        order = new Order();
        orderId = UUID.randomUUID();
        order.setId(orderId);
        order.setTotal(100.0);
        order.setUser(user);

        history = new History();
        history.setHistoryId(UUID.randomUUID());
        history.setAmount(order.getTotal());
        history.setPaymentDate(LocalDateTime.now());
        history.setOrder(order);
    }

    @Test
    void getHistories() {
        // Given
        List<History> histories = Collections.singletonList(history);
        when(historyRepository.findAll()).thenReturn(histories);

        // When
        List<HistoryResponse> result = historyService.getHistories();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order.getTotal(), result.get(0).getAmount());
        verify(historyRepository, times(1)).findAll();
    }

    @Test
    void getHistoriesByUserId() {
        // Given
        UUID userId = user.getId();
        List<History> histories = Collections.singletonList(history);
        when(historyRepository.findByUserId(userId)).thenReturn(histories);

        // When
        List<HistoryResponse> result = historyService.getHistoriesByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(historyRepository, times(1)).findByUserId(userId);
    }

    @Test
    void addHistoryByOrderId() {
        // Given
        when(orderService.getOrdersByOrderId(orderId)).thenReturn(order);
        when(historyRepository.save(any(History.class))).thenReturn(history);

        // When
        Optional<HistoryResponse> result = historyService.addHistoryByOrderId(orderId);

        // Then
        assertNotNull(result);
        verify(orderService, times(1)).getOrdersByOrderId(orderId);
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    void getHistoryByOrderId() {
        // given
        UUID orderId = order.getId();
        List<History> histories = Collections.singletonList(history);
        when(historyRepository.findByOrderId(orderId)).thenReturn(histories);

        // when
        List<HistoryResponse> result = historyService.getHistoriesByOrderId(orderId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(historyRepository, times(1)).findByOrderId(orderId);
    }
}
