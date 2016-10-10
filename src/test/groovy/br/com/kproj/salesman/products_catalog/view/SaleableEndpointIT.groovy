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

@Stepwise
@ClassReference(SaleableEndpoint)
class SaleableEndpointIT extends AbstractIntegrationTest {

    private static final String PRODUCTS = "/products_catalog/products.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    @Unroll("Buscar com a #uri no #scenary esperando o status #statusExpected")
    def "Busca de produtos/servicos e pacotes no sistema"() {
        setup:
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
            SceneryLoaderHelper.load(PRODUCTS)

        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = mvcResult.getResponse().getContentAsString()
            def statusResult = mvcResult.getResponse().getStatus()

        then:
            isEquals(jsonResult, scenery(scenary).getJson())
            statusResult == statusExpected.value()

        where:
            uri                     | scenary                                             || statusExpected
            "/rs/saleables"         | "Lista de todos os produtos cadastrados no sistema" || HttpStatus.OK
            "/rs/saleables/1"       | "Busca de produto por ID"                           || HttpStatus.OK
            "/rs/saleables/2"       | "Busca de servico por ID"                           || HttpStatus.OK
            "/rs/saleables/4"       | "Busca de pacote por ID"                            || HttpStatus.OK
    }


}
