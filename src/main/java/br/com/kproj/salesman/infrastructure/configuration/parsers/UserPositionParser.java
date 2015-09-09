package br.com.kproj.salesman.infrastructure.configuration.parsers;

import br.com.kproj.salesman.infrastructure.entity.UserPosition;
import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;
import com.google.common.collect.Lists;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class UserPositionParser {

	private static final String ATTRIBUTE_ID = "id";
	private static final String ATTRIBUTE_NAME = "name";
	private static final String FILE_NAME = "/configurations/user_positions.xml";
	
	
	public static List<UserPosition> getPositions() {

		return  loadUserPositions();
	}
	
	
	@SuppressWarnings("unchecked")
	protected static List<UserPosition> loadUserPositions() {
		SAXBuilder sab = new SAXBuilder();
		try {
			InputStream inputStream = findFile();
			
			if (inputStream == null)
				return Lists.newArrayList();
			
			Document doc = (Document) sab.build(inputStream);
			Element rootElement = (Element) doc.getRootElement();
			List<UserPosition> listPositions = new ArrayList<>();
			
			List<Element> childrenRoot = rootElement.getChildren();
			
			for (Element el : childrenRoot) {
				
				UserPosition possition = convertToPosition(el);
				listPositions.add(possition);
			}
			
			return listPositions;
		} catch (JDOMException e) {
			throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);
			
		} catch (IOException e) {
			throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
		}
	}
	
	private static UserPosition convertToPosition(Element element) {
		UserPosition position = new UserPosition();
		
		String name = element.getAttributeValue(ATTRIBUTE_NAME);
		String id = element.getAttributeValue(ATTRIBUTE_ID);

		position.setId(new Long(id));
        position.setName(name);
		
		return position;
		
	}
	
	private static InputStream findFile() {
		
		try {
			return UserPositionParser.class.getResourceAsStream(FILE_NAME);
		} catch (Exception e) {
			throw new InternalArchitectureException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
		}
		
	}
}
