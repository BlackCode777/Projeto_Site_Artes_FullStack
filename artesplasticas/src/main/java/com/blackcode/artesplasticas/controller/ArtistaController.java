package com.blackcode.artesplasticas.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackcode.artesplasticas.dto.ArtistaDTO;
import com.blackcode.artesplasticas.model.ArtistaModel;
import com.blackcode.artesplasticas.model.ObraModel;
import com.blackcode.artesplasticas.service.ArtistaService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping( value = "/artistas", produces = "application/json") // 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArtistaController {
  
  @Autowired
  private ArtistaService service;

  // Método para deletar um artista por ID.
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarArtista(@PathVariable Long id) {
		if (service.buscarArtistaPorId(id).isPresent()) {
			service.deletarArtista(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

  // Método para atualizar um artista.
	@PutMapping("/atualizarPorId/{id}")
	public ResponseEntity<ArtistaModel> atualizarArtistaPorID(@PathVariable Long id, @RequestBody ArtistaModel artista) {
	    return service.buscarArtistaPorId(id)
	        .map(existingArtista -> {
	            existingArtista.setNome(artista.getNome());
	            existingArtista.setBiografia(artista.getBiografia());
	            existingArtista.setContatoArtista(artista.getContatoArtista());
	            existingArtista.setRedesSociais(artista.getRedesSociais());

	            // Atualizar as obras do artista
	            if (artista.getObrasArtista() != null) {
	                for (ObraModel obra : artista.getObrasArtista()) {
	                    obra.setArtistaModel(existingArtista); // Configura a relação bidirecional
	                }
	                existingArtista.setObrasArtista(artista.getObrasArtista());
	            }

	            return ResponseEntity.ok(service.salvarArtista(existingArtista));
	        })
	        .orElse(ResponseEntity.notFound().build());
	}

  // Método auxiliar para validar Base64
	private boolean isValidBase64(String base64) {
	    try {
	        Base64.getDecoder().decode(base64);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}

	// Método para salvar um artista.
	//@CrossOrigin
    @PostMapping("/salvar")
	public ResponseEntity<?> salvarArtista(@RequestBody ArtistaModel artista) {
    	try {
            for (ObraModel obra : artista.getObrasArtista()) {
                if (obra.getImagemBase64() != null) {
                    byte[] imageBytes = Base64.getDecoder().decode(obra.getImagemBase64());
                    obra.setImagemBase64(imageBytes);
                }
                obra.setArtistaModel(artista);
            }

            ArtistaModel salvo = service.salvarArtista(artista);
            return ResponseEntity.ok(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro inesperado: " + e.getMessage());
        }
	}
	
  // Método para buscar um artista pelo ID.
	@GetMapping("/{id}")
	public ResponseEntity<ArtistaModel> buscarArtistaPorId(@PathVariable Long id) {
		return service.buscarArtistaPorId(id)
				.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/buscarTodos")
	public ResponseEntity<List<ArtistaDTO>> buscarArtistas() {
        List<ArtistaDTO> artistas = service.buscarArtistas();
        return ResponseEntity.ok(artistas);
    }

}
