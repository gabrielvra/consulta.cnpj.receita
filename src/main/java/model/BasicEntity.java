package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import tipo.Condicao;

/**
 * Entidade base para entidades do sistema
 * @author Gabriel Vieira (gabrielvra@outlook.com)
 *
 */
@MappedSuperclass
public abstract class BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Version
    @Column(name="versao", nullable= false)
    private Long version;
	
	@Enumerated(EnumType.STRING)
	@Column(name="condicao", nullable=false)
	private Condicao condicao = Condicao.VISIVEL;

    @Temporal(TemporalType.DATE)
	@Column(name="dt_criado")
	private Date criado;
    
    @Temporal(TemporalType.DATE)
	@Column(name="dt_atualizado")
	private Date atualizado;
	
	/**
	 * Retorna o ID da entidade
	 * @return {@link Long}
	 */
    public abstract Long getId();
    
    /**
     * Seta o ID da entidade
     * @param id
     */
    public abstract void setId(Long id);

    /**
     * Retorna a versão do objeto. <b> Esse campo é controlado pelo Hibernate</b>.
     * @return um número de controle da versão.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Seta a versão do objeto. <b>Esse campo é controlado pelo Hibernate</b>.
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

	public Condicao getCondicao() {
		return condicao;
	}

	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}  
	
	public Date getAtualizado() {
		return atualizado;
	}
	
	public void setAtualizado(Date atualizado) {
		this.atualizado = atualizado;
	}
	
	public Date getCriado() {
		return criado;
	}
	
	public void setCriado(Date criado) {
		this.criado = criado;
	}
}