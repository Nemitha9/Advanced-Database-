package com.mgmt.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Deposit;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, String>{

	Optional<Deposit> findTopByOrderByIdDesc();
	

}
