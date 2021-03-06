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

@ClassReference(RootTaskEndpoint)
class RootTaskEndpointIT extends AbstractIntegrationTest {

    private static final String ROOTTASK_LIST = "/products_catalog/task_definition/roottask-definitions.json"
    private static final String TASK_CREATE = "/products_catalog/task_definition/save/task-definition-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ROOTTASK_LIST)
        SceneryLoaderHelper.load(TASK_CREATE)
    }

    @Unroll
    def "Should find all root task definitions"() {
        given:
            def uri = "/rs/saleables/task-definitions/root-task-definitions"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()



            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())


        then:
            mvcResult.response.status == HttpStatus.OK.value
            jsonResult.uri == "/rs/saleables/task-definitions/root-task-definitions"
            jsonResult.items.size == 2

            jsonResult.items[0].id == 2
            jsonResult.items[0].task.id == 2
            jsonResult.items[0].task.title == "Root One"
            jsonResult.items[0].task.description == "Root One description"
            jsonResult.items[0].task.quantityDaysToFinish == 5
            jsonResult.items[0].links.size == 1
            jsonResult.items[0].links.find{it.rel == "children"}.href == "/rs/saleables/task-definitions/root-task-definitions/2/subtask-definitions"

            jsonResult.items[0].task.links.size == 2
            jsonResult.items[0].task.links.find{it.rel == "of-region"}.href == "/regions/1"
            jsonResult.items[0].task.links.find{it.rel == "of-saleable"}.href == "/saleables/2"

            jsonResult.items[1].id == 5
            jsonResult.items[1].task.id == 5
            jsonResult.items[1].task.title == "Root Two"
            jsonResult.items[1].task.description == "Root Two description"
            jsonResult.items[1].task.quantityDaysToFinish == 5
            jsonResult.items[1].links.size == 1
            jsonResult.items[1].links.find{it.rel == "children"}.href == "/rs/saleables/task-definitions/root-task-definitions/5/subtask-definitions"

            jsonResult.items[1].task.links.size == 2
            jsonResult.items[1].task.links.find{it.rel == "of-region"}.href == "/regions/1"
            jsonResult.items[1].task.links.find{it.rel == "of-saleable"}.href == "/saleables/2"

    }

    @Unroll
    def "Should find one root task definitions by ID"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/root-task-definitions/2")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def status = mvcResult.response.status

        expect:
            status == HttpStatus.OK.value
            jsonResult.item.id == 2
            jsonResult.item.task.id == 2
            jsonResult.item.task.title == "Root One"
            jsonResult.item.task.description == "Root One description"
            jsonResult.item.task.quantityDaysToFinish == 5
            jsonResult.item.task.links.find{it.rel == "of-region"}.href == "/regions/1"
            jsonResult.item.task.links.find{it.rel == "of-saleable"}.href == "/saleables/2"

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "children"}.href == "/rs/saleables/task-definitions/root-task-definitions/2/subtask-definitions"

            jsonResult.uri == "/rs/saleables/task-definitions/root-task-definitions/2"

    }

    @Unroll
    def "Should create a specialization root task definition"() {
        given: "A task definition created"
            def taskJson = new JsonSlurper().parseText(scenery("Criando uma nova task definition com todos os dados validos").json)

            def taskDefinitionCreated = mockMvc.perform(post("/rs/saleables/2/task-definitions")
                    .content(toJson(taskJson))
                    .contentType(MediaType.APPLICATION_JSON)).andReturn().response.getContentAsString()

        when: "Create a root task definition using a task ID"
            def taskDefinitionCreatedId = new JsonSlurper().parseText(taskDefinitionCreated).item.id

            def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/$taskDefinitionCreatedId/root-task-definitions")
                    .content("{\"task\":{\"id\":$taskDefinitionCreatedId}}")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def roottaskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "The result should be 201 and json with result root task definition"
            status == HttpStatus.CREATED.value
            roottaskCreated.item.id == taskDefinitionCreatedId

            roottaskCreated.item.task.id == taskDefinitionCreatedId
            roottaskCreated.item.task.title == taskJson.title
            roottaskCreated.item.task.description == taskJson.description
            roottaskCreated.item.task.quantityDaysToFinish == taskJson.quantityDaysToFinish

            roottaskCreated.item.task.links.find{it.rel == "of-region"}.href == "/regions/4"

            roottaskCreated.item.task.links.find{it.rel == "of-saleable"}.href == "/saleables/2"

            roottaskCreated.item.links[0].href == "/rs/saleables/task-definitions/root-task-definitions/$roottaskCreated.item.id/subtask-definitions"
            roottaskCreated.item.links[0].rel == "children"

            roottaskCreated.uri == "/rs/saleables/task-definitions/$roottaskCreated.item.id/root-task-definitions"
    }

    @Unroll
    def "Should not create a specialization of root task definition when invalid task definition ID"() {
        given: "A task definition ID invalid"
            def taskDefinitionId = 66666
            def uri = "/rs/saleables/task-definitions/$taskDefinitionId/root-task-definitions"

        when: "Try to Create a root task definition using a task dfinition with invalid id"

            def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/$taskDefinitionId/root-task-definitions")
                    .content("{\"task\":{\"id\":$taskDefinitionId}}")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def rootTaskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "The result should be 400 and json with error result"
            status == HttpStatus.BAD_REQUEST.value
            rootTaskCreated.errors.messages[0].message == "task.root.create.invalid.task"
            rootTaskCreated.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()

            rootTaskCreated.uri == uri
    }


}
