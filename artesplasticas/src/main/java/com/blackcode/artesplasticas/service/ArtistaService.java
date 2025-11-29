package com.blackcode.artesplasticas.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackcode.artesplasticas.dto.ArtistaDTO;
import com.blackcode.artesplasticas.dto.ObraDTO;
import com.blackcode.artesplasticas.model.ArtistaModel;
import com.blackcode.artesplasticas.model.ObraModel;
import com.blackcode.artesplasticas.repository.ArtistaRepository;
import com.blackcode.artesplasticas.repository.ObraRepository;

@Service
public class ArtistaService {
  
  @Autowired
	private ArtistaRepository repository;
	
	@Autowired
    private ObraRepository obraRepository;

  // Método para deletar um artista.
	public void deletarArtista(Long id) {
		repository.deleteById(id);
	}

	// Busca uma  lista de artistas cadastrados no banco
	public List<ArtistaModel> buscarTodosArtistas() {
		return repository.findAll();
	}

	public ArtistaModel save(ArtistaModel artista) {
		return repository.save(artista);
	}

  // Método para atualizar um artista.
  public ArtistaModel atualizarArtistaPorID(Long id) {
    ArtistaModel artistaAtualizado = repository.findById(id).get();
    return repository.save(artistaAtualizado);
  }

  // Método para salvar um artista.
	public ArtistaModel salvarArtista(ArtistaModel artista) {
        if (artista.getObrasArtista() != null) {
            for (ObraModel obra : artista.getObrasArtista()) {
                if (obra.getImagemBase64() != null) {
                    obra.setImagemBase64(Base64.getDecoder().decode(new String(obra.getImagemBase64())));
                }
                obra.setArtistaModel(artista);
            }
        }
        return repository.save(artista);
    }

  // Método para buscar um artista pelo ID.
	public Optional<ArtistaModel> buscarArtistaPorId(Long id) {
		return repository.findById(id);
	}
  
  // Método para buscar todos os artistas cadastrados.
  public List<ArtistaDTO> buscarArtistas() {
    List<ArtistaModel> artistas = repository.findAll();

    // Converte ArtistaModel para ArtistaDTO
    return artistas.stream().map(artista -> {
      ArtistaDTO artistaDTO = new ArtistaDTO();
      artistaDTO.setId(artista.getIdArtista());
      artistaDTO.setNome(artista.getNome());
      artistaDTO.setBiografia(artista.getBiografia());
      artistaDTO.setContatoArtista(artista.getContatoArtista());
      artistaDTO.setRedesSociais(artista.getRedesSociais());

      // Converte a lista de ObraModel para ObraDTO
      List<ObraDTO> obrasDTO = artista.getObrasArtista().stream().map(obra -> {
        ObraDTO obraDTO = new ObraDTO();
        obraDTO.setIdObra(obra.getIdObra());
        obraDTO.setTitulo(obra.getTitulo());
        obraDTO.setDescricao(obra.getDescricao());
        obraDTO.setTipo(obra.getTipo());
        obraDTO.setStatus(obra.getStatus());
        obraDTO.setDataCriacao(obra.getDataCriacao());
        obraDTO.setDimensaoQuadro(obra.getDimensaoQuadro());
        if (obra.getImagemBase64() != null) {
            obraDTO.setImagemBase64(Base64.getDecoder().decode(obra.getImagemBase64()));
        }
        obraDTO.setPreco(obra.getPreco());

        // Converte a imagem para Base64
        // if (obra.getImagemBase64() != null) {
        //   obraDTO.setImagemBase64(Base64.getEncoder().encodeToString(obra.getImagemBase64()));
        // }

        return obraDTO;
      }).collect(Collectors.toList());

      artistaDTO.setObrasArtista(obrasDTO);
      return artistaDTO;
    }).collect(Collectors.toList());
  }
    

}

