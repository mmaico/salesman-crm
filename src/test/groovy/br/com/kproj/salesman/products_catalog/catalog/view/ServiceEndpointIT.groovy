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
import spock.lang.Stepwise
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Stepwise
@ClassReference(SaleableEndpoint)
class ServiceEndpointIT extends AbstractIntegrationTest {

    private static final String PRODUCTS_CREATED = "/products_catalog/catalog/services-create.json";

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
    def "Creating a service"() {

        def mvcResult = mockMvc.perform(post("/rs/saleables")
                .content(scenery("Criacao de saleable para servico").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = mvcResult.getResponse().getContentAsString()
        def saleable = new JsonSlurper().parseText(jsonResult)

        def uri = "/rs/saleables/${saleable.item.id}/services"

        def serviceMvc = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def serviceResult = new JsonSlurper().parseText(serviceMvc.getResponse().getContentAsString())

        def serviceExpected = new JsonSlurper().parseText(scenery("Json de servico criado esperado").getJson())

        expect:
            serviceResult.item.id == saleable.item.id
            serviceResult.item.saleable.id == saleable.item.id
            serviceResult.item.saleable.name == serviceExpected.item.saleable.name
            serviceResult.item.saleable.description == serviceExpected.item.saleable.description
            serviceResult.item.saleable.active == serviceExpected.item.saleable.active
            serviceResult.item.saleable.price == serviceExpected.item.saleable.price
            serviceResult.item.saleable.priceCost == serviceExpected.item.saleable.priceCost
            serviceResult.uri == uri
    }

}
