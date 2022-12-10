package com.mgmt.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Requirement;

@Repository
public interface RequirementRepository extends CrudRepository<Requirement, String>{

	Optional<Requirement> findTopByOrderByIdDesc();
	Optional<Requirement> findByReqId(String reqId);
	Optional<List<Requirement>> findAllByCustomerId(String string);
	Optional<List<Requirement>> findAllByReqIdNotIn(List<String> appliedReqIds);

}
