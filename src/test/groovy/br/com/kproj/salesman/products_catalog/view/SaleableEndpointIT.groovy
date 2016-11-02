package br.com.kproj.salesman.products_catalog.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.domain.model.saleables.SaleableUnitRepository
import groovy.json.JsonSlurper
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
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Stepwise
@ClassReference(SaleableEndpoint)
class SaleableEndpointIT extends AbstractIntegrationTest {

    private static final String SALEABLES = "/products_catalog/saleables-list.json";
    private static final String SALEABLE_CREATE = "/products_catalog/saleables-create.json";


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(SALEABLES)
        SceneryLoaderHelper.load(SALEABLE_CREATE)
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

    def "Should create a saleable with all data" () {

        def saleable = new JsonSlurper().parseText(scenery("Criacao de saleable com todos os dados").getJson())
        def mvcResult = mockMvc.perform(post("/rs/saleables")
                .content(toJson(saleable))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()


        def saleableResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def statusResult = mvcResult.getResponse().getStatus()

        expect:
            statusResult == 201
            saleableResult.item.id != null
            saleableResult.item.name == saleable.name
            saleableResult.item.description == saleable.description
            saleableResult.item.active == saleable.active
            saleableResult.item.price == saleable.price
            saleableResult.item.priceCost == saleable.priceCost
    }

    def "Should not create a saleable without name" () {

        def saleable = new JsonSlurper().parseText(scenery("Criacao de saleable sem nome").getJson())
        def mvcResult = mockMvc.perform(post("/rs/saleables")
                .content(toJson(saleable))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()


        def saleableResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def statusResult = mvcResult.getResponse().getStatus()

        expect:
            statusResult == 400
            saleableResult.uri == "/rs/saleables"
            saleableResult.errors.messages[0].message == "saleable.without.name"
            saleableResult.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()
    }


}
