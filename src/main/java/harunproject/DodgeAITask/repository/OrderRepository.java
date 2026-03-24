package harunproject.DodgeAITask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import harunproject.DodgeAITask.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findByOrderNumber(String orderNumber);
}
