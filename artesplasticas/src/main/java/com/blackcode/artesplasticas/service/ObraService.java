package com.blackcode.artesplasticas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackcode.artesplasticas.model.ObraModel;
import com.blackcode.artesplasticas.repository.ObraRepository;

@Service
public class ObraService {

  @Autowired
	private ObraRepository repository;
	
	public List<ObraModel> buscarObras() {
		return repository.findAll();
	}
	
	// Método para buscar uma obra pelo ID.
	public Object buscarObraPorId(Long id) {
		return repository.findById(id);
	}
	
	// Método para salvar uma obra.
	public ObraModel salvarObra(ObraModel obra) {
		return repository.save(obra);
	}
	
	// Método para atualizar uma obra.
	public ObraModel atualizarObra(ObraModel obra) {
		return repository.save(obra);
	}
	
	// Método para deletar uma obra.
	public void deletarObra(Long id) {
		repository.deleteById(id);
	}
  
}
