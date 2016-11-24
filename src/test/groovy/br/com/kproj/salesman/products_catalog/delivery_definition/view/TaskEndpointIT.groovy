package br.com.kproj.salesman.products_catalog.delivery_definition.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ClassReference(TaskEndpoint)
class TaskEndpointIT extends AbstractIntegrationTest {

    private static final String TASK_LIST = "/products_catalog/task_definition/task-definition-list.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(TASK_LIST)
    }

    @Unroll
    def "Should find all task definitions by saleable"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/2/task-definitions")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        def jsonExpected = new JsonSlurper().parseText(scenery("Lista de todos os task definitions do sistema").json)

        def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())

        expect:
            jsonResult == jsonExpected
    }

    @Unroll
    def "Should find one subtask by ID"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/1")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonExpected = scenery("Busca por subtask definition por ID").json

        def jsonResult = mvcResult.getResponse().getContentAsString()

        expect:
            jsonResult == jsonExpected
    }

    @Unroll
    def "Should find one root task by ID in TaskEndpoint"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/2")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonExpected = scenery("Busca de root task definition pelo ID usando o TaskEndpoint").json

        def jsonResult = mvcResult.getResponse().getContentAsString()

        expect:
            jsonResult == jsonExpected
    }
}
