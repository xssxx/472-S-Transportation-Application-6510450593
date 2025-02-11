package ku.cs.transport_application.repository;

import ku.cs.transport_application.entity.History;
import ku.cs.transport_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByUserId(UUID userId);
}
