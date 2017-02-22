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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(SubtaskEndpoint)
class SubtaskEndpointIT extends AbstractIntegrationTest {

    private static final String SUBTASK_LIST = "/products_catalog/task_definition/subtask-definitions.json"
    private static final String TASK_CREATE = "/products_catalog/task_definition/save/task-definition-create.json"

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(SUBTASK_LIST)
        SceneryLoaderHelper.load(TASK_CREATE)
    }

    @Unroll
    "Should find all subtasks definitions by roottask in Endpoint"() {
        given:
            def uri = "/rs/saleables/task-definitions/root-task-definitions/2/subtask-definitions"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
        then:
            mvcResult.response.status == HttpStatus.OK.value
            jsonResult.uri == uri

            jsonResult.items.sort{it.id}
            jsonResult.items[0].id == 1
            jsonResult.items[0].task.id == 1
            jsonResult.items[0].task.title == "SubTask One"
            jsonResult.items[0].task.description == "Subtask One description"
            jsonResult.items[0].task.quantityDaysToFinish == 3
            jsonResult.items[0].links.find{it.rel == "child-of"}.href == "/rs/saleables/task-definitions/root-task-definitions/2"

            jsonResult.items[1].id == 3
            jsonResult.items[1].task.id == 3
            jsonResult.items[1].task.title == "SubTask Three"
            jsonResult.items[1].task.description == "Subtask Three description"
            jsonResult.items[1].task.quantityDaysToFinish == 2
            jsonResult.items[1].links.find{it.rel == "child-of"}.href == "/rs/saleables/task-definitions/root-task-definitions/2"

            jsonResult.items[2].id == 4
            jsonResult.items[2].task.id == 4
            jsonResult.items[2].task.title == "SubTask Four"
            jsonResult.items[2].task.description == "Subtask Four description"
            jsonResult.items[2].task.quantityDaysToFinish == 6
            jsonResult.items[2].links.find{it.rel == "child-of"}.href == "/rs/saleables/task-definitions/root-task-definitions/2"

    }

    @Unroll
    "Should find one sub task definitions by ID using SubtaskEnpoint"() {

        given:
            def uri = "/rs/saleables/task-definitions/root-task-definitions/subtask-definitions/4"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonExpected = new JsonSlurper().parseText(scenery("Busca por sub task definition pelo id no endpoint subtask").json)
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
        then:
            mvcResult.response.status == HttpStatus.OK.value
            jsonResult.item.task.links.sort{it.rel}

            jsonResult.item == jsonExpected.item
            jsonResult.uri == uri
    }

    @Unroll
    "Should create a specialization sub task definition"() {
        given: "A task definition created"
            def taskJson = new JsonSlurper().parseText(scenery("Criando uma nova task definition com todos os dados validos").json)
            def taskDefinitionCreated = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                    .content(toJson(taskJson))
                    .contentType(MediaType.APPLICATION_JSON)).andReturn().response.getContentAsString()

        when: "Create a subtask definition using a task ID"
            def taskDefinitionCreatedId = new JsonSlurper().parseText(taskDefinitionCreated).item.id

            def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/root-task-definitions/5/subtask-definitions")
                .content("{\"task\": {\"id\": $taskDefinitionCreatedId}}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "The result should be 201 and json with result sub task definition"
            subtaskCreated.item.id == taskDefinitionCreatedId

            subtaskCreated.item.task.id == taskDefinitionCreatedId
            subtaskCreated.item.task.title == taskJson.title
            subtaskCreated.item.task.description == taskJson.description
            subtaskCreated.item.task.quantityDaysToFinish == taskJson.quantityDaysToFinish

            subtaskCreated.item.links[0].href == "/rs/saleables/task-definitions/root-task-definitions/5"
            subtaskCreated.item.links[0].rel == "child-of"

            subtaskCreated.item.task.links.sort{it.rel}
            subtaskCreated.item.task.links[0].href == "/regions/4"
            subtaskCreated.item.task.links[0].rel == "of-region"

            subtaskCreated.item.task.links[1].href == "/saleables/2"
            subtaskCreated.item.task.links[1].rel == "of-saleable"

            subtaskCreated.uri == "/rs/saleables/task-definitions/root-task-definitions/5/subtask-definitions"

            status == HttpStatus.CREATED.value

    }

    @Unroll
    "Should not create a specialization sub task definition with ID task with already with a specialization"() {
        given: "A task definition created"
            def subTaskDefinitionAlreadySpecializationID = 3

        when: "Create a subtask definition using a task ID"

            def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/root-task-definitions/5/subtask-definitions")
                    .content("{\"task\": {\"id\": $subTaskDefinitionAlreadySpecializationID}}")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "The result should be 400 and json error"
            status == HttpStatus.BAD_REQUEST.value
            subtaskCreated.errors.messages[0].message == "subtask.with.taskid.already.specialization"
            subtaskCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()

            subtaskCreated.uri == "/rs/saleables/task-definitions/root-task-definitions/5/subtask-definitions"
    }

    @Unroll
    "Should not create a specialization of sub task definition when invalid root task definition"() {
        given: "A task definition created"
            def taskJson = new JsonSlurper().parseText(scenery("Criando uma nova task definition com todos os dados validos").json)
            def taskDefinitionCreated = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                    .content(toJson(taskJson))
                    .contentType(MediaType.APPLICATION_JSON)).andReturn().response.getContentAsString()

        when: "Try to Create a subtask definition using a task and invalid root id"
            def taskDefinitionCreatedId = new JsonSlurper().parseText(taskDefinitionCreated).item.id

            def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/root-task-definitions/6666/subtask-definitions")
                    .content("{\"task\": {\"id\": $taskDefinitionCreatedId}}")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "The result should be 400 and json with result sub task definition"
            status == HttpStatus.BAD_REQUEST.value
            subtaskCreated.errors.messages[0].message == "subtask.create.invalid.roottask"
            subtaskCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()

            subtaskCreated.uri == "/rs/saleables/task-definitions/root-task-definitions/6666/subtask-definitions"
    }

}
