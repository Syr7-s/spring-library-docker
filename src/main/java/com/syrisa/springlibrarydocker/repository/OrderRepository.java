package com.syrisa.springlibrarydocker.repository;

import com.syrisa.springlibrarydocker.model.impl.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
    Page<Orders> findAll(Pageable pageable);
}
