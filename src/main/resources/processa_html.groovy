import javax.swing.text.html.HTMLDocument;
import javax.swing.text.Segment;
import model.FornecedorDadosBean;

public class ProcessaHTML{
	
	private String NUMERO_INSCRICAO = "NÚMERO DE INSCRIÇÃO  ";
	private String DATA_ABERTURA = "DATA DE ABERTURA  ";
	private String NOME_EMPRESARIAL = "NOME EMPRESARIAL  ";
	private String NOME_FANTASIA = "TÍTULO DO ESTABELECIMENTO (NOME DE FANTASIA)  ";
	private String ATIVIDADE_ECONOMICA_PRINCIPAL = "CÓDIGO E DESCRIÇÃO DA ATIVIDADE ECONÔMICA PRINCIPAL  ";
	private String ATIVIDADE_ECONOMICA_SECUNDARIA = "CÓDIGO E DESCRIÇÃO DAS ATIVIDADES ECONÔMICAS SECUNDÁRIAS  ";
	private String NATUREZA_JURIDICA = "CÓDIGO E DESCRIÇÃO DA NATUREZA JURÍDICA  ";
	private String LOGRADOURO = "LOGRADOURO  ";
	private String NUMERO = "NÚMERO  ";
	private String COMPLEMENTO = "COMPLEMENTO  ";
	private String CEP = "CEP  ";
	private String BAIRRO = "BAIRRO/DISTRITO  ";
	private String MUNICIPIO  = "MUNICÍPIO  ";
	private String UF = "UF  ";
	private String ENDERECO_ELETRONICO = "ENDEREÇO ELETRÔNICO  ";
	private String TELEFONE = "TELEFONE  ";
	private String ENTE_FEDERATIVO = "ENTE FEDERATIVO RESPONSÁVEL (EFR)  ";
	private String SITUACAO_CADASTRAL = "SITUAÇÃO CADASTRAL  ";
	private String DATA_SITUACAO_CADASTRAL = "DATA DA SITUAÇÃO CADASTRAL  ";
	private String MOTIVO_SITUACAO_CADASTRAL = "MOTIVO DE SITUAÇÃO CADASTRAL  ";
	private String SITUACAO_ESPECIAL = "SITUAÇÃO ESPECIAL  ";
	private String DATA_SITUACAO_ESPECIAL = "DATA DA SITUAÇÃO ESPECIAL  ";
	private String ERRO_CONSULTA = "Erro na Consulta";
	
	def FornecedorDadosBean process(HTMLDocument document){
		FornecedorDadosBean fornecedorDadosBean = new FornecedorDadosBean();
		
		Segment seg = new Segment();
		document.getText(0, document.getEndPosition().getOffset(), seg);
		StringBuilder sbTituloDado = new StringBuilder();
		StringBuilder sbDado = new StringBuilder();
		for (int i = 0; i < seg.array.length; i++) {
			char value = seg.array[i];
			if (value == '\n') {
				if (!sbDado.toString().isEmpty()) {
					String informacao = sbDado.toString().trim();
					if (sbTituloDado.toString().equals(ERRO_CONSULTA)) {
						fornecedorDadosBean.setError(true);
						break;
					} else if (sbTituloDado.toString().equals(NUMERO_INSCRICAO)) {
						fornecedorDadosBean.setNumeroInscricao(informacao.replaceAll("\\D", ""));
						fornecedorDadosBean.setEmpresaTipo(informacao.replaceAll("[^a-zA-Z]", ""));
					} else if (sbTituloDado.toString().equals(DATA_ABERTURA)) {
						fornecedorDadosBean.setDataAbertura(informacao);
					} else if (sbTituloDado.toString().equals(NOME_EMPRESARIAL)) {
						fornecedorDadosBean.setNomeEmpresarial(informacao);
					} else if (sbTituloDado.toString().equals(NOME_FANTASIA)) {
						fornecedorDadosBean.setNomeFantasia(informacao);
					} else if (sbTituloDado.toString().equals(ATIVIDADE_ECONOMICA_PRINCIPAL)) {
						fornecedorDadosBean.setAtividadeEconomicaPrincipal(informacao);
					} else if (sbTituloDado.toString().equals(ATIVIDADE_ECONOMICA_SECUNDARIA)) {
						if (informacao != null) {
							informacao = informacao.replaceAll("  ", "\n");
						}                				
						fornecedorDadosBean.setAtividadeEconomicaSecundaria(informacao);
					} else if (sbTituloDado.toString().equals(NATUREZA_JURIDICA)) {
						fornecedorDadosBean.setNaturezaJuridica(informacao);
					} else if (sbTituloDado.toString().equals(LOGRADOURO)) {
						fornecedorDadosBean.setLogradouro(informacao);
					} else if (sbTituloDado.toString().equals(NUMERO)) {
						fornecedorDadosBean.setNumero(informacao);
					} else if (sbTituloDado.toString().equals(COMPLEMENTO)) {
						fornecedorDadosBean.setComplemento(informacao);
					} else if (sbTituloDado.toString().equals(CEP)) {
						fornecedorDadosBean.setCep(informacao);
					} else if (sbTituloDado.toString().equals(BAIRRO)) {
						fornecedorDadosBean.setBairro(informacao);
					} else if (sbTituloDado.toString().equals(MUNICIPIO)) {
						fornecedorDadosBean.setMunicipio(informacao);
					} else if (sbTituloDado.toString().equals(UF)) {
						fornecedorDadosBean.setUf(informacao);
					} else if (sbTituloDado.toString().equals(ENDERECO_ELETRONICO)) {
						fornecedorDadosBean.setEnderecoEletronico(informacao);
					} else if (sbTituloDado.toString().equals(TELEFONE)) {
						fornecedorDadosBean.setTelefone(informacao);
					} else if (sbTituloDado.toString().equals(ENTE_FEDERATIVO)) {
						fornecedorDadosBean.setEnteFederativo(informacao);
					} else if (sbTituloDado.toString().equals(SITUACAO_CADASTRAL)) {
						fornecedorDadosBean.setSituacaoCadastral(informacao);
					} else if (sbTituloDado.toString().equals(DATA_SITUACAO_CADASTRAL)) {
						fornecedorDadosBean.setDataSituacaoCadastral(informacao);
					} else if (sbTituloDado.toString().equals(MOTIVO_SITUACAO_CADASTRAL)) {
						fornecedorDadosBean.setMotivoSituacaoCadastral(informacao);
					} else if (sbTituloDado.toString().equals(SITUACAO_ESPECIAL)) {
						fornecedorDadosBean.setSituacaoEspecial(informacao);
					} else if (sbTituloDado.toString().equals(DATA_SITUACAO_ESPECIAL)) {
						fornecedorDadosBean.setDataSituacaoEspecial(informacao);
					}
				}
				sbDado = new StringBuilder();
				sbTituloDado = new StringBuilder();
			} else {
				if (isTituloIdentificado(sbTituloDado.toString())) {
					sbDado.append(value);
				} else {
					sbTituloDado.append(value);
				}
			}
		}
		return fornecedorDadosBean;
	}
	
	/**
	 * Verifica se o título processado foi encontrato.
	 * @param value - Instância de {@link String}
	 * @return Instância de {@link Boolean}
	 */
	def Boolean isTituloIdentificado(String value) {
	    	return value.equals(NUMERO_INSCRICAO) || value.equals(DATA_ABERTURA) || value.equals(NOME_EMPRESARIAL) || 
	    			value.equals(NATUREZA_JURIDICA) || value.equals(NOME_FANTASIA) || value.equals(ATIVIDADE_ECONOMICA_PRINCIPAL) || value.equals(ATIVIDADE_ECONOMICA_SECUNDARIA) || 
	    			value.equals(LOGRADOURO) || value.equals(NUMERO) || value.equals(COMPLEMENTO) || value.equals(CEP) || 
	    			value.equals(BAIRRO) || value.equals(MUNICIPIO) || value.equals(UF) || value.equals(ENDERECO_ELETRONICO) || 
	    			value.equals(TELEFONE) || value.equals(ENTE_FEDERATIVO) || value.equals(SITUACAO_CADASTRAL) || value.equals(DATA_SITUACAO_CADASTRAL) || 
	    			value.equals(MOTIVO_SITUACAO_CADASTRAL) || value.equals(SITUACAO_ESPECIAL) || value.equals(DATA_SITUACAO_ESPECIAL) || value.equals(ERRO_CONSULTA);
	}
}	


HTMLDocument document = input;
def ProcessaHTML p = new ProcessaHTML();
output = p.process(document);
	