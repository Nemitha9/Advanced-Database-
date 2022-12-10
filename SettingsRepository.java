package com.mgmt.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgmt.model.Settings;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, String>{

	Optional<Settings> findTopByOrderByIdDesc();

}
