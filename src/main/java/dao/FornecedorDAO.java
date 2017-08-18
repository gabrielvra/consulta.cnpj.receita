package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import model.FornecedorDadosBean;
import model.FornecedorDadosEntity;

/**
 * Especialização de {@link AbstractDAO} para {@link FornecedorDadosEntity}
 * @author Gabriel Vieira - gabrielvra@outlook.com
 */
public class FornecedorDAO extends AbstractDAO<FornecedorDadosEntity> {

	@Override
	public Class<FornecedorDadosEntity> getEntityClass() {
		return FornecedorDadosEntity.class;
	}

	/**
	 * Busca uma empresa pelo CNPJ.
	 * @param cnpj - Instância de {@link String}
	 * @return Instância de {@link FornecedorDadosEntity}
	 */
	public FornecedorDadosEntity findByCNPJ(String cnpj) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FornecedorDadosEntity> criteriaQuery =  builder.createQuery(FornecedorDadosEntity.class);
		Root<FornecedorDadosEntity> c = criteriaQuery.from(FornecedorDadosEntity.class);
		criteriaQuery.select(c);
		ParameterExpression<String> p1 = builder.parameter(String.class);
		criteriaQuery.where(builder.like(c.<String>get("numeroInscricao"), p1));
		TypedQuery<FornecedorDadosEntity> query = getEntityManager().createQuery(criteriaQuery);
		query.setParameter(p1, cnpj);
		List<FornecedorDadosEntity> results = query.getResultList();
		return results != null && !results.isEmpty() ? results.get(0) : null;
	}
	
	/**
	 * Busca um fornecedor pelo CNPJ utilizando a data atual. Considera válido os fornecedores que tenham data de validade de 10 dias.
	 * @param cnpj - Instância de {@link String}
	 * @return Instância de {@link FornecedorDadosEntity}
	 * TODO Permitir configurar os dias em uma tabela no banco de dados
	 */
	public FornecedorDadosEntity findByCNPJData(String cnpj) {
		FornecedorDadosEntity fornecedorEntity = findByCNPJ(cnpj);
		if (fornecedorEntity != null) {
			LocalDate dataAtual = LocalDate.now();
			LocalDate dataCriacao = new java.sql.Date(fornecedorEntity.getCriado().getTime()).toLocalDate();
			long diferencaEmDias = ChronoUnit.DAYS.between(dataCriacao, dataAtual);
			//Se for maior que 10, não retorna fornecedor, obrigando ir ao site da receita
			if (diferencaEmDias > 10) {
				return null;
			}
			return fornecedorEntity;
		}
		return null;
	}
	
	@Override
	protected void atualizar(FornecedorDadosEntity entity) {
		FornecedorDadosEntity fornecedor = findByCNPJ(entity.getNumeroInscricao());
		if (fornecedor != null) {
			getEntityManager().remove(fornecedor);
		}
	}
	
	/**
	 * Conversão da entidade para bean
	 * @param entity - Instância de {@link FornecedorDadosEntity}
	 * @return Instância de {@link FornecedorDadosBean}
	 */
	public static FornecedorDadosBean transformFrom(FornecedorDadosEntity entity) {
		FornecedorDadosBean fornecedorBean = new FornecedorDadosBean();
		fornecedorBean.setNumeroInscricao(entity.getNumeroInscricao());
		fornecedorBean.setEmpresaTipo(entity.getEmpresaTipo());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		fornecedorBean.setDataAbertura(formatter.format(entity.getDataAbertura()));
		fornecedorBean.setNomeEmpresarial(entity.getNomeEmpresarial());
		fornecedorBean.setNomeFantasia(entity.getNomeFantasia());
		fornecedorBean.setAtividadeEconomicaPrincipal(entity.getAtividadeEconomicaPrincipal());
		fornecedorBean.setAtividadeEconomicaSecundaria(entity.getAtividadeEconomicaSecundaria());
		fornecedorBean.setNaturezaJuridica(entity.getNaturezaJuridica());
		fornecedorBean.setLogradouro(entity.getLogradouro());
		fornecedorBean.setNumero(entity.getNumero());
		fornecedorBean.setComplemento(entity.getComplemento());
		fornecedorBean.setCep(entity.getCep());
		fornecedorBean.setBairro(entity.getBairro());
		fornecedorBean.setMunicipio(entity.getMunicipio());
		fornecedorBean.setUf(entity.getUf());
		fornecedorBean.setEnderecoEletronico(entity.getEnderecoEletronico());
		fornecedorBean.setTelefone(entity.getTelefone());
		fornecedorBean.setEnteFederativo(entity.getEnteFederativo());
		fornecedorBean.setSituacaoCadastral(entity.getSituacaoCadastral());
		try {
			fornecedorBean.setDataSituacaoCadastral(formatter.format(entity.getDataSituacaoCadastral()));
		} catch (Exception e) {
			fornecedorBean.setDataSituacaoCadastral("");
			System.err.println("Conversão de data situação cadastral: "+e.getMessage());
		}
		fornecedorBean.setMotivoSituacaoCadastral(entity.getMotivoSituacaoCadastral());
		fornecedorBean.setSituacaoEspecial(entity.getSituacaoEspecial());
		try {
			fornecedorBean.setDataSituacaoEspecial(formatter.format(entity.getDataSituacaoEspecial()));
		} catch (Exception e) {
			fornecedorBean.setDataSituacaoEspecial("");
			System.err.println("Conversão de data situação especial: "+e.getMessage());
		}
		try {
			fornecedorBean.setCriadoEm(formatter.format(entity.getCriado()));
		} catch (Exception e) {
			fornecedorBean.setCriadoEm("");
			System.err.println("Conversão de data de controle de criação de registro: "+e.getMessage());
		}
		return fornecedorBean;
	}

	/**
	 * Conversão de bean para entidade
	 * @param fornecedorBean - Instância de {@link FornecedorDadosBean}
	 * @return Instância de {@link FornecedorDadosEntity}
	 * @throws ParseException
	 */
	public static FornecedorDadosEntity transformTo(FornecedorDadosBean fornecedorBean) throws ParseException {
		FornecedorDadosEntity fornecedorEntity = new FornecedorDadosEntity();
		fornecedorEntity.setNumeroInscricao(fornecedorBean.getNumeroInscricao());
		fornecedorEntity.setNumeroInscricao(fornecedorBean.getNumeroInscricao());
		fornecedorEntity.setEmpresaTipo(fornecedorBean.getEmpresaTipo());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		fornecedorEntity.setDataAbertura(formatter.parse(fornecedorBean.getDataAbertura()));
		fornecedorEntity.setNomeEmpresarial(fornecedorBean.getNomeEmpresarial());
		fornecedorEntity.setNomeFantasia(fornecedorBean.getNomeFantasia());
		fornecedorEntity.setAtividadeEconomicaPrincipal(fornecedorBean.getAtividadeEconomicaPrincipal());
		fornecedorEntity.setAtividadeEconomicaSecundaria(fornecedorBean.getAtividadeEconomicaSecundaria());
		fornecedorEntity.setNaturezaJuridica(fornecedorBean.getNaturezaJuridica());
		fornecedorEntity.setLogradouro(fornecedorBean.getLogradouro());
		fornecedorEntity.setNumero(fornecedorBean.getNumero());
		fornecedorEntity.setComplemento(fornecedorBean.getComplemento());
		fornecedorEntity.setCep(fornecedorBean.getCep());
		fornecedorEntity.setBairro(fornecedorBean.getBairro());
		fornecedorEntity.setMunicipio(fornecedorBean.getMunicipio());
		fornecedorEntity.setUf(fornecedorBean.getUf());
		fornecedorEntity.setEnderecoEletronico(fornecedorBean.getEnderecoEletronico());
		fornecedorEntity.setTelefone(fornecedorBean.getTelefone());
		fornecedorEntity.setEnteFederativo(fornecedorBean.getEnteFederativo());
		fornecedorEntity.setSituacaoCadastral(fornecedorBean.getSituacaoCadastral());
		try {
			fornecedorEntity.setDataSituacaoCadastral(formatter.parse(fornecedorBean.getDataSituacaoCadastral()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fornecedorEntity.setMotivoSituacaoCadastral(fornecedorBean.getMotivoSituacaoCadastral());
		fornecedorEntity.setSituacaoEspecial(fornecedorBean.getSituacaoEspecial());
		try {
			fornecedorEntity.setDataSituacaoEspecial(formatter.parse(fornecedorBean.getDataSituacaoEspecial()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return fornecedorEntity;
	}
}