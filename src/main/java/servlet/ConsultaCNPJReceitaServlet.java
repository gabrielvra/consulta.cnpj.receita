package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import dao.FornecedorDAO;
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import model.FornecedorDadosBean;
import model.FornecedorDadosEntity;

/**
 * Serviço de pesquisa do CNPJ do fornecedor após o usuário inserir os dados no formulário.
 * @author Gabriel Vieira - gabrielvra@outlook.com
 */
@WebServlet(asyncSupported = false, name = "consultaCNPJ", urlPatterns = {"/consultaCNPJ"})
public class ConsultaCNPJReceitaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
        	String cnpj = request.getParameter("cnpj");
        	cnpj = cnpj.replaceAll("\\D", "");
        	if (!ConsultaCNPJUtils.isValidCNPJ(cnpj)){
    			request.setAttribute("mensagem", "CNPJ inválido, tente novamente.");      
    			request.setAttribute("tipoMensagem", "alert-warning");
            	request.getRequestDispatcher("main.jsp").forward(request, response);      		
        	}
        	String captcha = request.getParameter("captcha");
        	FornecedorDAO fornecedorDAO = new FornecedorDAO();
        	//Se não foi informado a captcha, tenta consultar o fornecedor pelo CNPJ
        	if (captcha == null || captcha.isEmpty()) {
            	FornecedorDadosEntity fornecedorEntity = fornecedorDAO.findByCNPJData(cnpj);
        		if (fornecedorEntity != null) {
        			FornecedorDadosBean fornecedorBean = FornecedorDAO.transformFrom(fornecedorEntity);
                	request.setAttribute("fornecedorBean", fornecedorBean);
                	request.getRequestDispatcher("showdata.jsp").forward(request, response);
        		} else {
        			//Voltar para a página principal, habilitar o captcha e mostrar mensagem que deve ser inserido pois não foi encontrado
        			request.setAttribute("cnpj", ConsultaCNPJUtils.formatCNPJ(cnpj));
        			request.setAttribute("showCaptcha", "block");    
        			request.setAttribute("mensagem", "CNPJ não encontrado, informe os caracteres da imagem.");         
        			request.setAttribute("tipoMensagem", "alert-info");
                	request.getRequestDispatcher("main.jsp").forward(request, response);
        		}
        	} else {
        		//Se captcha foi inserida, então deve consultar direto no site da receita
                HttpPost buscaDados = new HttpPost("http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/valida.asp");  
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("origem", "comprovante"));  
                nameValuePairs.add(new BasicNameValuePair("search_type", "cnpj"));  
                nameValuePairs.add(new BasicNameValuePair("cnpj", cnpj));    
                nameValuePairs.add(new BasicNameValuePair("txtTexto_captcha_serpro_gov_br", captcha));
                nameValuePairs.add(new BasicNameValuePair("submit1", "Consultar"));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
                buscaDados.setEntity(urlEncodedFormEntity);  
                HttpResponse resposta = ConsultaCNPJURL.get().getClient().execute(buscaDados, ConsultaCNPJURL.get().getContext());
                if (resposta.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entidade = resposta.getEntity();  
                    String html = EntityUtils.toString(entidade);  
                    //Iniciar processamento do HTML
                    HTMLDocument document = ConsultaCNPJUtils.getHTMLDocument(html);  
                    //Inicializa o groovy para processamento do HTML via script.
        			GroovyScriptEngine gse = new GroovyScriptEngine(new String[] { "src/main/resources/processa_html.groovy" });
        			Binding binding = new Binding();
        			binding.setVariable("input", document);
        			gse.run("processa_html.groovy", binding);
        			//Resultado do bean vindo do groovy
        			FornecedorDadosBean fornecedorBean = (FornecedorDadosBean) binding.getVariable("output");
        			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        			fornecedorBean.setCriadoEm(formatter.format(new Date()));
        			//Verifica se o HTML apresentou alguma mensagem indicando erro.
                	if (fornecedorBean.getError()) {
                		//Se foi identificado mensagem de erro no HTML, exibe mensagem de erro de captcha.
            			request.setAttribute("cnpj", ConsultaCNPJUtils.formatCNPJ(cnpj));
            			request.setAttribute("showCaptcha", "block");    
            			request.setAttribute("mensagem", "Captcha inválida, tente novamente.");      
            			request.setAttribute("tipoMensagem", "alert-warning");
            			ConsultaCaptchaServlet.getCaptcha();
                    	request.getRequestDispatcher("main.jsp").forward(request, response);
                	} else if (!fornecedorBean.exists()) {
                		//Se não ocorreu erro na consulta mas não conseguiu preencher as informações, indica erro na leitura do hmtl
            			request.setAttribute("mensagem", "Ocorreu um erro na leitura da página da receita federal.");   
            			request.setAttribute("tipoMensagem", "alert-danger");      
                    	request.getRequestDispatcher("main.jsp").forward(request, response);
                	} else {
                		//Está tudo correto, pode inserir o fornecedor no banco e apresentar os dados na tela.
                		FornecedorDadosEntity fornecedorEntity = FornecedorDAO.transformTo(fornecedorBean);
                		fornecedorDAO.persist(fornecedorEntity);
                    	request.setAttribute("fornecedorBean", fornecedorBean);
                    	request.getRequestDispatcher("showdata.jsp").forward(request, response);
                	}
                } else {
        			//Falha nos parâmetros ou o sistema da receita está fora
        			request.setAttribute("mensagem", "Falha ao obter os dados do endereço da receita federal. <br/>" + resposta.getStatusLine().getStatusCode());    
        			request.setAttribute("tipoMensagem", "alert-danger");      
                	request.getRequestDispatcher("main.jsp").forward(request, response);
                }
        	}
        } catch (Exception e) {
        	//Caso ocorrer algum erro, deve voltar a página principal.
			request.setAttribute("mensagem", "Ocorreu um erro inesperado, contate suporte técnico. <br/>" + e.getMessage());               	
			request.setAttribute("tipoMensagem", "alert-danger");      
        	request.getRequestDispatcher("main.jsp").forward(request, response);
		} finally {
			
        }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 
    @Override
    public String getServletInfo() {
        return "Serviço de pesquisa de CNPJ";
    }
}