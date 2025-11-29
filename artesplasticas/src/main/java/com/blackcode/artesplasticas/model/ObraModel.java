package com.blackcode.artesplasticas.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import net.coobird.thumbnailator.Thumbnails;

@Entity
@Table(name = "obra")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ObraModel {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObra;
    private String titulo;
    private String status;
    private String tipo;
    private String descricao;
    private String dataCriacao;
    
    @Column(name = "imagem_base64", columnDefinition = "BYTEA")
    private String imagemBase64;
    
    private String dimensaoQuadro;
    private Double preco;

    @JoinColumn(name = "idArtista")
    @ManyToOne
    @JsonBackReference
    private ArtistaModel  artistaModel;

    public ObraModel() {}
	
	// Construtor
	public ObraModel(String titulo, String descricao, String imagemBase64, String tipo, String status,
        String dataCriacao, String dimensaoQuadro, Double preco, ArtistaModel artistaModel) {
      this.titulo = titulo;
      this.descricao = descricao;
      this.imagemBase64 = imagemBase64;
      this.tipo = tipo;
      this.status = status;
      this.dataCriacao = dataCriacao;
      this.dimensaoQuadro = dimensaoQuadro;
      this.preco = preco;
        this.artistaModel = artistaModel;
    }
  
     // Getters e Setters
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemBase64() {
    	if (this.imagemBase64 != null && this.imagemBase64.contains(tipo)) {
            return this.imagemBase64;
        }
        return null;
    }

    private String detectImageType(String imagemBase642) {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(imagemBase642))) {
        	String formatName = ImageIO.getImageReaders(ImageIO.createImageInputStream(bais)).next().getFormatName();

            // Validar se o formato é suportado
            if ("jpeg".equalsIgnoreCase(formatName) || "jpg".equalsIgnoreCase(formatName) || "png".equalsIgnoreCase(formatName)) {
                return formatName.toLowerCase(); // Retorna o formato em letras minúsculas
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Retorna null se o formato não for reconhecido
    }
    
    private boolean isValidBase64(String base64) {
        try {
            Base64.getDecoder().decode(base64);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getImagemBase64String() {
        if (this.imagemBase64 != null && this.imagemBase64.contains(tipo) && isValidBase64(this.imagemBase64)) {
            try {
                // Detectar o tipo de imagem
                String fileType = detectImageType(this.imagemBase64);

                if (fileType == null) {
                    throw new RuntimeException("Imagem inválida. O formato da imagem deve ser JPEG, JPG ou PNG.");
                }

                // Adicionar o prefixo correto ao Base64
                String base64Prefix = "data:image/" + fileType + ";base64,";
                return base64Prefix + Base64.getEncoder().encodeToString(Base64.getDecoder().decode(this.imagemBase64));

            } catch (Exception e) {
                throw new RuntimeException("Erro ao processar a imagem: " + e.getMessage(), e);
            }
        }
        return null;
    }

	public void setImagemBase64(byte[] imageBytes) {    	    	
        byte[] imageBytes1 = Base64.getDecoder().decode(imageBytes);
        
        if (imageBytes1.length > 5 * 1024 * 1024) { // 5 MB limite
            throw new RuntimeException("Imagem muito grande. O tamanho máximo permitido é 5MB. BACKEND");
        }
        try {
            byte[] imageBytes11 = Base64.getDecoder().decode(imageBytes1);
            
            InputStream inputStream = new ByteArrayInputStream(imageBytes11);

            // Redimensionar imagem usando Thumbnailator
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(inputStream)
                      .size(800, 800)
                      .outputQuality(0.8)
                      .toOutputStream(outputStream);

            this.imagemBase64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IllegalArgumentException | IOException e) {
            throw new RuntimeException("Erro ao processar imagem base64: " + e.getMessage());
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDimensaoQuadro() {
        return dimensaoQuadro;
    }

    public void setDimensaoQuadro(String dimensaoQuadro) {
        this.dimensaoQuadro = dimensaoQuadro;
    }
    
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
    
    public ArtistaModel getArtistaModel() {
        return artistaModel;
    }

    public void setArtistaModel(ArtistaModel artistaModel) {
        this.artistaModel = artistaModel;
    }

}
