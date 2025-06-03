package org.example.ch25.data;

import org.springframework.data.repository.CrudRepository;
import org.example.ch25.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>{
}
