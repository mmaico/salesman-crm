package br.com.kproj.salesman.infrastructure.configuration.parsers;

import br.com.kproj.salesman.infrastructure.entity.location.State;
import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class StateParser {
    private static final String FILE_NAME = "/configurations/states.xml";

    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_ID = "id";

    public static List<State> getStates() {

        return loadStates();
    }


    @SuppressWarnings("unchecked")
    protected static List<State> loadStates() {
        SAXBuilder sab = new SAXBuilder();
        try {
            InputStream inputStream = findFile();
            Document doc = (Document) sab.build(inputStream);
            Element rootElement = (Element) doc.getRootElement();

            List<Element> children = rootElement.getChildren();
            List<State> states = new ArrayList<State>();

            for (Element el : children) {
                State state = convertToState(el);
                states.add(state);
            }

            return states;
        } catch (JDOMException e) {
            throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);

        } catch (IOException e) {
            throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
        }

    }

    private static State convertToState(Element element) {
        State state = new State();
        String name = element.getAttributeValue(ATTRIBUTE_NAME);
        String id = element.getAttributeValue(ATTRIBUTE_ID);

        state.setName(name);
        state.setId(new Long(id));

        return state;

    }


    private static InputStream findFile() {

        try {
            return StateParser.class.getResourceAsStream(FILE_NAME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
        }

    }
}
