package com.blackcode.artesplasticas.dto;

import java.util.Base64;

public class ObraDTO {
  
  private Long idObra;
    private String titulo;
    private String status;
    private String tipo;    
    private String descricao;
    private String dataCriacao;
    private String imagemBase64;
    private String dimensaoQuadro;
    private Double preco;

    public Long getIdObra() {
        return idObra;
    }

    public void setIdObra(Long idObra) {
        this.idObra = idObra;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(byte[] imagemBase64) {
        if (imagemBase64 != null) {
            this.imagemBase64 = Base64.getEncoder().encodeToString(imagemBase64);
        } else {
            this.imagemBase64 = null;
        }
    }
   
    public String getDimensaoQuadro() {
        return dimensaoQuadro;
    }

    /**
     * @param dimensaoQuadro the dimensaoQuadro to set
     */
    public void setDimensaoQuadro(String dimensaoQuadro) {
        this.dimensaoQuadro = dimensaoQuadro;
    }

    /**
     * @return Double return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public String getImagemBase64String() {
        return imagemBase64;
	}

}













