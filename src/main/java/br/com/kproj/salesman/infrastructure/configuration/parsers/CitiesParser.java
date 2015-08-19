package br.com.kproj.salesman.infrastructure.configuration.parsers;

import br.com.kproj.salesman.infrastructure.entity.location.City;
import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CitiesParser {

    private static final String FILE_NAME = "/configurations/cities.xml";


    public static List<City> getCities() {

        return loadCities();
    }


    @SuppressWarnings("unchecked")
    protected static List<City> loadCities() {
        SAXBuilder sab = new SAXBuilder();
        try {
            InputStream inputStream = findFile();
            Document doc = (Document) sab.build(inputStream);
            Element rootElement = (Element) doc.getRootElement();
            List<City> listCities = new ArrayList<City>();

            List<Element> childrenRoot = rootElement.getChildren();

            for (Element el : childrenRoot) {

                City city = convertToCity(el);
                listCities.add(city);
            }

            return listCities;
        } catch (JDOMException e) {
            throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);

        } catch (IOException e) {
            throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static City convertToCity(Element element) {

        List<Element> children = element.getChildren();
        Element stateAcronym = children.get(0);
        Element code = children.get(1);
        Element name = children.get(2);

        City city = new City();
        city.setCode(code.getText());
        city.setName(name.getText());
        city.setStateAcronym(stateAcronym.getText());

        return city;

    }
    private static InputStream findFile() {

        try {
            return CitiesParser.class.getResourceAsStream(FILE_NAME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
        }

    }

}
