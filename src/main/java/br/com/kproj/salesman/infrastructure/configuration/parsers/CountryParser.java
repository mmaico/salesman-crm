package br.com.kproj.salesman.infrastructure.configuration.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.kproj.salesman.infrastructure.entity.location.Country;
import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class CountryParser {

	private static final String ATTRIBUTE_CODE = "id";
	private static final String ATTRIBUTE_BACEN_CODE = "bacenCode";
	private static final String ATTRIBUTE_NAME_PT = "name_pt";
	private static final String FILE_NAME = "/configurations/countries.xml";
	
	
	public static List<Country> getCountries() {

		return  loadCountry();
	}
	
	
	@SuppressWarnings("unchecked")
	protected static List<Country> loadCountry() {
		SAXBuilder sab = new SAXBuilder();
		try {
			InputStream inputStream = findFile();
			
			if (inputStream == null)
				return new ArrayList<Country>();
			
			Document doc = (Document) sab.build(inputStream);
			Element rootElement = (Element) doc.getRootElement();
			List<Country> listCountry = new ArrayList<Country>();
			
			List<Element> childrenRoot = rootElement.getChildren();
			
			for (Element el : childrenRoot) {
				
				Country country = convertToCountry(el);
				listCountry.add(country);
			}
			
			return listCountry;
		} catch (JDOMException e) {
			throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);
			
		} catch (IOException e) {
			throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
		}
	}
	
	private static Country convertToCountry(Element element) {
		Country country = new Country();
		
		String namePT = element.getAttributeValue(ATTRIBUTE_NAME_PT);
		String id = element.getAttributeValue(ATTRIBUTE_CODE);
		String codeBacen = element.getAttributeValue(ATTRIBUTE_BACEN_CODE);

		country.setId(new Long(id));
		country.setBacenCode(codeBacen);
        country.setName(namePT);
		
		return country;
		
	}
	
	private static InputStream findFile() {
		
		try {
			return CountryParser.class.getResourceAsStream(FILE_NAME);
		} catch (Exception e) {
			throw new InternalArchitectureException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
		}
		
	}
}
