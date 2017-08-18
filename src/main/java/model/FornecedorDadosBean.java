package model;

/**
 * Armazena os dados da empresa para transferências
 * @author Gabriel Vieira (gabrielvra@outlook.com)
 *
 */
public class FornecedorDadosBean {

	private String numeroInscricao;
	private String empresaTipo;
	private String dataAbertura;
	private String nomeEmpresarial;
	private String nomeFantasia;
	private String atividadeEconomicaPrincipal;
	private String atividadeEconomicaSecundaria;
	private String naturezaJuridica;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String municipio;
	private String uf;
	private String enderecoEletronico;
	private String telefone;
	private String enteFederativo;
	private String situacaoCadastral;
	private String dataSituacaoCadastral;
	private String motivoSituacaoCadastral;
	private String situacaoEspecial;
	private String dataSituacaoEspecial;
	private String criadoEm;
	private Boolean error = false;
	
	public void setEmpresaTipo(String empresaTipo) {
		this.empresaTipo = empresaTipo;
	}
	
	public String getEmpresaTipo() {
		return empresaTipo;
	}
	
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	
	public String getDataInscricao() {
		return dataAbertura;
	}
	
	public void setDataAbertura(String dataAbertura) {
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
	
	public String getDataSituacaoCadastral() {
		return dataSituacaoCadastral;
	}
	
	public void setDataSituacaoCadastral(String dataSituacaoCadastral) {
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
	
	public String getDataSituacaoEspecial() {
		return dataSituacaoEspecial;
	}
	
	public void setDataSituacaoEspecial(String dataSituacaoEspecial) {
		this.dataSituacaoEspecial = dataSituacaoEspecial;
	}
	
	public String getDataAbertura() {
		return dataAbertura;
	}
	
	public Boolean exists() {
		return this.numeroInscricao != null;
	}

	public void setError(Boolean error) {
		this.error = error;
	}
	
	public Boolean getError() {
		return error;
	}
	
	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	public String getCriadoEm() {
		return criadoEm;
	}
}