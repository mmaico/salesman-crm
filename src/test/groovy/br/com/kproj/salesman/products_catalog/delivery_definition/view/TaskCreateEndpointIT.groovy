package br.com.kproj.salesman.products_catalog.delivery_definition.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnitRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(TaskEndpoint)
class TaskCreateEndpointIT extends AbstractIntegrationTest {

    private static final String TASK_CREATE = "/products_catalog/task_definition/save/task-definition-create.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(TASK_CREATE)
    }

    @Unroll
    def "Create a new task definition with all valid data"() {
        def taskDefinitionData = new JsonSlurper().parseText(scenery("Criando uma nova task definition com todos os dados validos").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                .content(toJson(taskDefinitionData))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def taskDefinitionCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        expect:
            mvcResult.response.status == HttpStatus.CREATED.value()
            taskDefinitionCreated.item.id != null
            taskDefinitionCreated.item.title == taskDefinitionData.title
            taskDefinitionCreated.item.description == taskDefinitionData.description
            taskDefinitionCreated.item.quantityDaysToFinish == taskDefinitionData.quantityDaysToFinish

            taskDefinitionCreated.item.links.size == 2
            taskDefinitionCreated.item.links.find{it.rel == "of-region"}.href == "/regions/4"

            taskDefinitionCreated.item.links.find{it.rel == "of-saleable"}.href == "/saleables/2"
            taskDefinitionCreated.uri == "/rs/saleables/2/task-definitions"
    }

    @Unroll
    def "Should not create a task definition when not have a region"() {
        def taskDefinitionData = new JsonSlurper().parseText(scenery("Criando uma nova task definition com region nao existente").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                .content(toJson(taskDefinitionData))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def taskDefinitionCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        expect:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value()
            taskDefinitionCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()
            taskDefinitionCreated.errors.messages[0].message == "task.definition.invalid.region"
            taskDefinitionCreated.uri == "/rs/saleables/2/task-definitions"
    }

    @Unroll
    def "Should not create a task definition when not have a title"() {
        def taskDefinitionData = new JsonSlurper().parseText(scenery("Criando uma nova task definition sem o titulo").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                .content(toJson(taskDefinitionData))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def taskDefinitionCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        expect:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value()
            taskDefinitionCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()
            taskDefinitionCreated.errors.messages[0].message == "task.definition.invalid.title"
            taskDefinitionCreated.uri == "/rs/saleables/2/task-definitions"
    }

    @Unroll
    def "Should not create a task definition when invalid a saleable"() {
        def taskDefinitionData = new JsonSlurper().parseText(scenery("Criando uma nova task definition com saleable invalido").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/6666/task-definitions")
                .content(toJson(taskDefinitionData))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def taskDefinitionCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        expect:
        mvcResult.response.status == HttpStatus.BAD_REQUEST.value()
        taskDefinitionCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()
        taskDefinitionCreated.errors.messages[0].message == "task.definition.invalid.saleable"
        taskDefinitionCreated.uri == "/rs/saleables/6666/task-definitions"
    }

    @Unroll
    def "Create a new task definition with only required information"() {
        def taskDefinitionData = new JsonSlurper().parseText(scenery("Criando uma nova task definition somente com os campos obrigatorios").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                .content(toJson(taskDefinitionData))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def taskDefinitionCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        expect:
            mvcResult.response.status == HttpStatus.CREATED.value()
            taskDefinitionCreated.item.id != null
            taskDefinitionCreated.item.title == taskDefinitionData.title
            taskDefinitionCreated.item.quantityDaysToFinish == 0

            taskDefinitionCreated.item.links.size == 2
            taskDefinitionCreated.item.links.find{it.rel == "of-region"}.href == "/regions/4"

            taskDefinitionCreated.item.links.find{it.rel == "of-saleable"}.href == "/saleables/2"
            taskDefinitionCreated.uri == "/rs/saleables/2/task-definitions"
    }

}
