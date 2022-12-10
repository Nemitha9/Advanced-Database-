package com.mgmt.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{

	Optional<Customer> findTopByOrderByIdDesc();

	Optional<Customer> findByEmailIdAndPassword(String emailId, String password);

	Optional<Customer> findByCustomerId(String string);


}
