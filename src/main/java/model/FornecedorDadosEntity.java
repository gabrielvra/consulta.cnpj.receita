package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import tipo.Tamanho;

/**
 * Representa uma entidade para armazenar os dados da empresa
 * @author Gabriel Vieira (gabrielvra@outlook.com)
 *
 */
@Entity
@Table(name="fornecedor_dados")
public class FornecedorDadosEntity extends BasicEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", nullable=false)
	@SequenceGenerator(name="sq_fornecedor_dados", sequenceName="sq_fornecedor_dados")
	@GeneratedValue(generator="sq_fornecedor_dados", strategy=GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(name="ds_numero_inscricao", nullable=false, length=Tamanho.SIZE_50)
	private String numeroInscricao;
	
	@Column(name="ds_empresa_tipo", nullable=true, length=Tamanho.SIZE_50)
	private String empresaTipo;

    @Temporal(TemporalType.DATE)
	@Column(name="dt_abertura")
	private Date dataAbertura;
    
	@Column(name="ds_nome_empresarial", nullable=true, length=Tamanho.SIZE_200)
	private String nomeEmpresarial;

	@Column(name="ds_nome_fantasia", nullable=true, length=Tamanho.SIZE_200)
	private String nomeFantasia;
	
	@Column(name="ds_atividade_economica_principal", nullable=true, length=Tamanho.SIZE_500)
	private String atividadeEconomicaPrincipal;
	
	@Column(name="ds_atividade_economica_secundaria", nullable=true, length=Tamanho.SIZE_5000)
	private String atividadeEconomicaSecundaria;

	@Column(name="ds_natureza_juridica", nullable=true, length=Tamanho.SIZE_200)
	private String naturezaJuridica;

	@Column(name="ds_logradouro", nullable=true, length=Tamanho.SIZE_500)
	private String logradouro;

	@Column(name="ds_numero", nullable=true, length=Tamanho.SIZE_500)
	private String numero;

	@Column(name="ds_complemento", nullable=true, length=Tamanho.SIZE_500)
	private String complemento;

	@Column(name="ds_cep", nullable=true, length=Tamanho.SIZE_50)
	private String cep;

	@Column(name="ds_bairro", nullable=true, length=Tamanho.SIZE_100)
	private String bairro;

	@Column(name="ds_municipio", nullable=true, length=Tamanho.SIZE_100)
	private String municipio;

	@Column(name="ds_uf", nullable=true, length=Tamanho.SIZE_50)
	private String uf;
	
	@Column(name="ds_endereco_eletronico", nullable=true, length=Tamanho.SIZE_100)
	private String enderecoEletronico;

	@Column(name="ds_telefone", nullable=true, length=Tamanho.SIZE_50)
	private String telefone;
	
	@Column(name="ds_ente_deferativo", nullable=true, length=Tamanho.SIZE_50)
	private String enteFederativo;
	
	@Column(name="ds_situacao_cadastral", nullable=true, length=Tamanho.SIZE_50)
	private String situacaoCadastral;
	
    @Temporal(TemporalType.DATE)
	@Column(name="dt_situacao_cadastral")
	private Date dataSituacaoCadastral;
	
	@Column(name="ds_motivo_situacao_cadastral", nullable=true, length=Tamanho.SIZE_50)
	private String motivoSituacaoCadastral;
	
	@Column(name="ds_situacao_especial", nullable=true, length=Tamanho.SIZE_50)
	private String situacaoEspecial;
	
    @Temporal(TemporalType.DATE)
	@Column(name="dt_situacao_especial")
	private Date dataSituacaoEspecial;
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}

	public String getEmpresaTipo() {
		return empresaTipo;
	}

	public void setEmpresaTipo(String empresaTipo) {
		this.empresaTipo = empresaTipo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getAtividadeEconomicaPrincipal() {
		return atividadeEconomicaPrincipal;
	}

	public void setAtividadeEconomicaPrincipal(String atividadeEconomicaPrincipal) {
		this.atividadeEconomicaPrincipal = atividadeEconomicaPrincipal;
	}

	public String getAtividadeEconomicaSecundaria() {
		return atividadeEconomicaSecundaria;
	}

	public void setAtividadeEconomicaSecundaria(String atividadeEconomicaSecundaria) {
		this.atividadeEconomicaSecundaria = atividadeEconomicaSecundaria;
	}

	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEnderecoEletronico() {
		return enderecoEletronico;
	}

	public void setEnderecoEletronico(String enderecoEletronico) {
		this.enderecoEletronico = enderecoEletronico;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEnteFederativo() {
		return enteFederativo;
	}

	public void setEnteFederativo(String enteFederativo) {
		this.enteFederativo = enteFederativo;
	}

	public String getSituacaoCadastral() {
		return situacaoCadastral;
	}

	public void setSituacaoCadastral(String situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}

	public Date getDataSituacaoCadastral() {
		return dataSituacaoCadastral;
	}

	public void setDataSituacaoCadastral(Date dataSituacaoCadastral) {
		this.dataSituacaoCadastral = dataSituacaoCadastral;
	}

	public String getMotivoSituacaoCadastral() {
		return motivoSituacaoCadastral;
	}

	public void setMotivoSituacaoCadastral(String motivoSituacaoCadastral) {
		this.motivoSituacaoCadastral = motivoSituacaoCadastral;
	}

	public String getSituacaoEspecial() {
		return situacaoEspecial;
	}

	public void setSituacaoEspecial(String situacaoEspecial) {
		this.situacaoEspecial = situacaoEspecial;
	}

	public Date getDataSituacaoEspecial() {
		return dataSituacaoEspecial;
	}

	public void setDataSituacaoEspecial(Date dataSituacaoEspecial) {
		this.dataSituacaoEspecial = dataSituacaoEspecial;
	}
}