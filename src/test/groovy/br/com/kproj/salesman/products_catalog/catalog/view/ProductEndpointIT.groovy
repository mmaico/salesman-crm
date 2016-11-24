package br.com.kproj.salesman.products_catalog.catalog.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnitRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(SaleableEndpoint)
class ProductEndpointIT extends AbstractIntegrationTest {

    private static final String PRODUCTS_CREATED = "/products_catalog/catalog/products-create.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(PRODUCTS_CREATED)
    }

    @Unroll
    def "Creating a product"() {

        def mvcResult = mockMvc.perform(post("/rs/saleables")
                .content(scenery("Criacao de saleable").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = mvcResult.getResponse().getContentAsString()
        def saleable = new JsonSlurper().parseText(jsonResult)

        def uri = "/rs/saleables/${saleable.item.id}/products"
        def unit = scenery("Criacao de especializacao de saleable para produto").getJson()

        def productMvc = mockMvc.perform(post(uri).content(unit)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def productResult = new JsonSlurper().parseText(productMvc.getResponse().getContentAsString())

        def productExpected = new JsonSlurper().parseText(scenery("Json de produto criado esperado").getJson())

        expect:
            productResult.item.id == saleable.item.id
            productResult.item.saleable.id == saleable.item.id
            productResult.item.saleable.name == productExpected.item.saleable.name
            productResult.item.saleable.description == productExpected.item.saleable.description
            productResult.item.saleable.active == productExpected.item.saleable.active
            productResult.item.saleable.price == productExpected.item.saleable.price
            productResult.item.saleable.priceCost == productExpected.item.saleable.priceCost
            productResult.uri == uri
    }

}
