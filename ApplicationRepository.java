package com.mgmt.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Application;
import com.mgmt.model.Invoice;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, String>{

	Optional<Application> findTopByOrderByIdDesc();

	Optional<Application> findByAppId(String string);

	Optional<List<Application>> findAllByTransId(String string);

	Optional<List<Application>> findAllByReqId(String reqId);

	Optional<Application> findByReqId(String reqId);

	Optional<Application> findTopByOrderByReqIdDesc(String reqId);



}
