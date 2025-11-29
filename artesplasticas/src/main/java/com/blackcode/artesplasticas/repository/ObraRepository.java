package com.blackcode.artesplasticas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackcode.artesplasticas.model.ObraModel;

public interface ObraRepository extends JpaRepository<ObraModel, Long> {
  
  @Override
  Optional<ObraModel> findById(Long id);

	@Override
	List<ObraModel> findAll();

	@Override
	void deleteById(Long id);

}
