package br.com.kproj.salesman.products_catalog.delivery_definition.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Stepwise
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@Stepwise
@ClassReference(RootTaskEndpoint)
class RootTaskEndpointIT extends AbstractIntegrationTest {

    private static final String ROOTTASK_LIST = "/products_catalog/task_definition/roottask-definitions.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ROOTTASK_LIST)
    }

    @Unroll
    def "Should find all root task definitions"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/root-task-definitions")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        def jsonExpected = scenery("Lista de todos os root tasks definitions do sistema").json

        def jsonResult = mvcResult.response.getContentAsString()
        def status = mvcResult.response.status

        expect:
            jsonResult == jsonExpected
            status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find one root task definitions by ID"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/root-task-definitions/2")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        def jsonExpected = scenery("Busca por root task definition por ID em rootendpoint").json

        def jsonResult = mvcResult.getResponse().getContentAsString()
        def status = mvcResult.response.status

        expect:
            jsonResult == jsonExpected
            status == HttpStatus.OK.value
    }

}
