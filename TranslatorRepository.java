package com.mgmt.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Translator;

@Repository
public interface TranslatorRepository extends CrudRepository<Translator, String>{

	Optional<Translator> findTopByOrderByIdDesc();

	Optional<Translator> findByEmailIdAndPassword(String emailId, String password);

}
