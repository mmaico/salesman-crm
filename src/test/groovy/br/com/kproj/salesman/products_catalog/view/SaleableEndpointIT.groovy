package br.com.kproj.salesman.products_catalog.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Stepwise
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.JsonCompareUtil.isEquals
import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Stepwise
@ClassReference(SaleableEndpoint)
class SaleableEndpointIT extends AbstractIntegrationTest {

    private static final String PRODUCTS = "/products_catalog/products.json";
    private static final String PRODUCTS_CREATED = "/products_catalog/products-create.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()

        SceneryLoaderHelper.load(PRODUCTS)
        SceneryLoaderHelper.load(PRODUCTS_CREATED)
    }

    @Unroll("Search with #uri in #scenary expecting a status #statusExpected")
    def "Search for the products/services and packages in the system"() {

        when: "a request made to #uri"
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = mvcResult.getResponse().getContentAsString()
            def statusResult = mvcResult.getResponse().getStatus()

        then: "the result should be equals #scenary"
            isEquals(jsonResult, scenery(scenary).getJson())
            statusResult == statusExpected.value()

        where:
            uri                     | scenary                                             || statusExpected
            "/rs/saleables"         | "Lista de todos os produtos cadastrados no sistema" || HttpStatus.OK
            "/rs/saleables/1"       | "Busca de produto por ID"                           || HttpStatus.OK
            "/rs/saleables/2"       | "Busca de servico por ID"                           || HttpStatus.OK
            "/rs/saleables/4"       | "Busca de pacote por ID"                            || HttpStatus.OK
            "/rs/saleables/9999"    | "Busca de saleable que nao existe"                  || HttpStatus.NOT_FOUND
    }

    def "Creating a product"() {
        def mvcResult = mockMvc.perform(post("/rs/saleable")
                .content(scenery("Criacao de saleable").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = mvcResult.getResponse().getContentAsString()


    }

}
