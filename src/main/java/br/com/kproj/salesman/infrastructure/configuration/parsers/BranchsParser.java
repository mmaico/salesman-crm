package br.com.kproj.salesman.infrastructure.configuration.parsers;

import br.com.kproj.salesman.infrastructure.entity.BranchEntity;
import br.com.kproj.salesman.infrastructure.exceptions.InternalArchitectureException;
import com.google.common.collect.Lists;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class BranchsParser {

    private static final String ID = "id";
    private static final String NAME="name";
    private static final String FILE_NAME = "/configurations/user_branch.xml";


    public static List<BranchEntity> getBranchs() {

        return loadBranchs();
    }


    @SuppressWarnings("unchecked")
    protected static List<BranchEntity> loadBranchs() {
        SAXBuilder sab = new SAXBuilder();
        try {
            InputStream inputStream = findFile();
            Document doc = (Document) sab.build(inputStream);
            Element rootElement = (Element) doc.getRootElement();
            List<BranchEntity> listBranchs = Lists.newArrayList();

            List<Element> childrenRoot = rootElement.getChildren();

            for (Element el : childrenRoot) {

                BranchEntity branch = convertToBranch(el);
                listBranchs.add(branch);
            }

            return listBranchs;
        } catch (JDOMException e) {
            throw new InternalArchitectureException("Erro ao obter as configuracoes.", e);

        } catch (IOException e) {
            throw new InternalArchitectureException("Erro ao obter o arquivos de configuracoes.", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static BranchEntity convertToBranch(Element element) {
        String name = element.getAttributeValue(NAME);
        String id = element.getAttributeValue(ID);

        BranchEntity branch = new BranchEntity();
        branch.setId(new Long(id));
        branch.setName(name);

        return branch;

    }
    private static InputStream findFile() {

        try {
            return BranchsParser.class.getResourceAsStream(FILE_NAME);
        } catch (Exception e) {
            throw new IllegalArgumentException("Nao foi possivel encontrar o arquivo:" + FILE_NAME);
        }

    }

}
