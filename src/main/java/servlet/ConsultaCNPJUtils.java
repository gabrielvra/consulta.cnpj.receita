package servlet;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 * Funções comuns utilizadas para a consulta do CNPJ
 * @author Gabriel Vieira - gabrielvra@outlook.com
 *
 */
public class ConsultaCNPJUtils {

	/**
	 * Processa uma string que contém informações em HTML para um {@link HTMLDocument}
	 * @param value - Instância de {@link String}
	 * @return Instância de {@link HTMLDocument}
	 * @throws IOException
	 * @throws BadLocationException
	 */
	public static final HTMLDocument getHTMLDocument(String value) throws IOException, BadLocationException {
		Reader stringReader = new StringReader(value);
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		htmlKit.read(stringReader, htmlDoc, 0);
		return htmlDoc;
	}
	
	/**
	 * Formata uma String para CNPJ
	 * @param cnpj - Instância de {@link String}
	 * @return Instância de {@link String}
	 */
	public static String formatCNPJ(String cnpj) {
		Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})");
		Matcher matcher = pattern.matcher(cnpj);
		if(matcher.find()){
			return matcher.replaceAll("$1.$2.$3/$4-$5");
		}
		return cnpj;
	}
	
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}
}