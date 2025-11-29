package com.blackcode.artesplasticas.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "artista")
public class ArtistaModel implements Serializable  {
  
  private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idArtista;
	
	private String nome;
	private String telefone;
	private String biografia;
	private String contatoArtista;
	private String redesSociais;

    @OneToMany(mappedBy = "artistaModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObraModel> obrasArtista;

    public ArtistaModel() {
    }
    
    public ArtistaModel(Long idArtista, String nome, String telefone, String biografia, String contatoArtista,
        String redesSociais, List<ObraModel> obrasArtista) {
      this.idArtista = idArtista;
      this.nome = nome;
      this.telefone = telefone;
      this.biografia = biografia;
      this.contatoArtista = contatoArtista;
      this.redesSociais = redesSociais;
      this.obrasArtista = obrasArtista;
    }
    
    /**
     * @return Long return the idArtista
     */
    public Long getIdArtista() {
        return idArtista;
    }

    /**
     * @param idArtista the idArtista to set
     */
    public void setIdArtista(Long idArtista) {
        this.idArtista = idArtista;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return String return the biografia
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * @param biografia the biografia to set
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * @return String return the contatoArtista
     */
    public String getContatoArtista() {
        return contatoArtista;
    }

    /**
     * @param contatoArtista the contatoArtista to set
     */
    public void setContatoArtista(String contatoArtista) {
        this.contatoArtista = contatoArtista;
    }

    /**
     * @return String return the redesSociais
     */
    public String getRedesSociais() {
        return redesSociais;
    }

    /**
     * @param redesSociais the redesSociais to set
     */
    public void setRedesSociais(String redesSociais) {
        this.redesSociais = redesSociais;
    }

    /**
     * @return List<ObraModel> return the obrasArtista
     */
    public List<ObraModel> getObrasArtista() {
        return obrasArtista;
    }

    /**
     * @param obrasArtista the obrasArtista to set
     */
    public void setObrasArtista(List<ObraModel> obrasArtista) {
        this.obrasArtista = obrasArtista;
    }

}

