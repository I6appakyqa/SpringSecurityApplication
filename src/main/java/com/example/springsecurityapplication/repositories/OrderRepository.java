package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findById(int id);
    List<Order> findByPerson(Person person);


    @Modifying
    @Query(value = "delete from orders where person_id=?1", nativeQuery = true)
    void deleteOrdersByPersonId(int id);

    //Поиск всех заказов по пользователю
//    @Query(value = "select * from orders group by number where person_id = ?1", nativeQuery = true)
    @Query(value = "select * from orders where person_id = ?1", nativeQuery = true)
    List<Order> findOrdersByPersonId(int id);


}
