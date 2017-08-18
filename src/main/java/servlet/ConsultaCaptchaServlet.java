package servlet;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.google.common.io.Files;

/**
 * Serviço de consulta de captcha no site da receita federal
 * @author Gabriel Vieira - gabrielvra@outlook.com
 *
 */
@WebServlet(asyncSupported = false, name = "consultaCaptcha", urlPatterns = {"/"})
public class ConsultaCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = 2L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getCaptcha();
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/main.jsp");
	    rd.forward(req, resp);
	}

	/**
	 * Busca a captcha no servidor da receita federal
	 */
	public static void getCaptcha() {
        try {
        	ConsultaCNPJURL.get().init();
      
        	HttpGet consultaFormulario = new HttpGet("http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/cnpjreva_solicitacao2.asp");
            HttpResponse response = ConsultaCNPJURL.get().getClient().execute(consultaFormulario, ConsultaCNPJURL.get().getContext());
            HttpEntity entidade = response.getEntity();
            String html = EntityUtils.toString(entidade);
            HTMLDocument document = ConsultaCNPJUtils.getHTMLDocument(html);
            ElementIterator elementIterator = new ElementIterator(document);
            Element element;
            String imgcaptcha = "";
            //Montar o link para consultar a captcha
            while ((element = elementIterator.next()) != null) {
                 if (element.getName().equals(HTML.Tag.IMG.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.ID)).equalsIgnoreCase("imgcaptcha")) {
                	 String imageLink = String.valueOf(element.getAttributes().getAttribute(HTML.Attribute.SRC));
                	 imageLink = imageLink.replaceAll(Pattern.quote("./"), "/");
                     imgcaptcha = "http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva"+imageLink;
                     break;
                 }
            }
            //Buscar a captcha com base nos dados obtidos acima.
            HttpGet consultaCaptcha = new HttpGet(imgcaptcha);
            response = ConsultaCNPJURL.get().getClient().execute(consultaCaptcha, ConsultaCNPJURL.get().getContext());  
            entidade = response.getEntity();
            byte[] captcha = EntityUtils.toByteArray(entidade);
       	    File targetFile = new File("src/main/webapp/captcha.png");
       	    Files.write(captcha, targetFile);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}