package harunproject.DodgeAITask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import harunproject.DodgeAITask.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByOrderId(Long orderId);
}
