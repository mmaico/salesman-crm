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
@ClassReference(SubtaskEndpoint)
class SubtaskEndpointIT extends AbstractIntegrationTest {

    private static final String SUBTASK_LIST = "/products_catalog/task_definition/subtask-definitions.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(SUBTASK_LIST)
    }

    @Unroll
    def "Should find all subtasks definitions by roottask in Endpoint"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/root-task-definitions/2")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        def jsonExpected = scenery("Lista de todos as subtasks definitions de um roottask").json

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
