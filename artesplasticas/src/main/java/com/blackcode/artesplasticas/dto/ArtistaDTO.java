package com.blackcode.artesplasticas.dto;

import java.util.List;

public class ArtistaDTO {
  
  private Long id;
    private String nome;
    private String biografia;
    private String contatoArtista;
    private String redesSociais;
    private List<ObraDTO> obrasArtista;

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return List<ObraDTO> return the obrasArtista
     */
    public List<ObraDTO> getObrasArtista() {
        return obrasArtista;
    }

    /**
     * @param obrasArtista the obrasArtista to set
     */
    public void setObrasArtista(List<ObraDTO> obrasArtista) {
        this.obrasArtista = obrasArtista;
    }

}
