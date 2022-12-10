package com.mgmt.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String>{

	Optional<Invoice> findTopByOrderByIdDesc();


	Optional<List<Invoice>> findAllByCustomerId(String customerId);
	Optional<List<Invoice>> findAllByTransId(String transId);


	Optional<Invoice> findByReqId(String reqId);


	Optional<Invoice> findByInvId(String invId);


}
