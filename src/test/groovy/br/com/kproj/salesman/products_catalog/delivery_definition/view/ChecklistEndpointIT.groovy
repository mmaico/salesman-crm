package br.com.kproj.salesman.products_catalog.delivery_definition.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Stepwise
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

@Stepwise
@ClassReference(ChecklistEndpoint)
class ChecklistEndpointIT extends AbstractIntegrationTest {

    private static final String CHECKLIST_LIST = "/products_catalog/task_definition/checklist/checklist-definitions.json"
    private static final String TASK_CREATE = "/products_catalog/task_definition/save/task-definition-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CHECKLIST_LIST)
        SceneryLoaderHelper.load(TASK_CREATE)
    }

    @Unroll
    def "Should find all checklist definitions of a task"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/2/checklist-definitions")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonExpected = scenery("Lista de todos as checklist definitions de uma task definition").json

        def jsonResult = mvcResult.response.getContentAsString()
        def status = mvcResult.response.status

        expect:
            jsonResult == jsonExpected
            status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find checklist definitions of ID"() {

        def mvcResult = mockMvc.perform(get("/rs/saleables/task-definitions/checklist-definitions/4")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonExpected = scenery("Busca por uma checklist definition pelo id").json

        def jsonResult = mvcResult.response.getContentAsString()
        def status = mvcResult.response.status

        expect:
            jsonResult == jsonExpected
            status == HttpStatus.OK.value
    }

    @Unroll
    def "Should create a checklist definitions of a task"() {
        def checklist = new JsonSlurper().parseText(scenery("Criar uma checklist com todos os dados").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/5/checklist-definitions")
                .content(toJson(checklist))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def status = mvcResult.response.status

        expect:
            status == HttpStatus.CREATED.value()
            jsonResult.item.name == checklist.name
            jsonResult.item.id != null
            jsonResult.item.links.size == 1
            jsonResult.item.links[0].href == "/saleables/task-definitions/5"
            jsonResult.item.links[0].rel == "of-task"
            jsonResult.uri == "/rs/saleables/task-definitions/5/checklist-definitions"
    }

    @Unroll
    def "Should not create a checklist definitions of a task without name"() {
        def checklist = new JsonSlurper().parseText(scenery("Criar uma checklist sem o nome").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/5/checklist-definitions")
                .content(toJson(checklist))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def status = mvcResult.response.status

        expect:
            status == HttpStatus.BAD_REQUEST.value()
            jsonResult.errors.messages[0].message == "checklist.definition.invalid.name"
            jsonResult.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()

            jsonResult.uri == "/rs/saleables/task-definitions/5/checklist-definitions"
    }

    @Unroll
    def "Should not create a checklist definitions of a task when invalid task definition"() {
        def checklist = new JsonSlurper().parseText(scenery("Criar uma checklist com todos os dados").json)

        def mvcResult = mockMvc.perform(post("/rs/saleables/task-definitions/9999/checklist-definitions")
                .content(toJson(checklist))
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def status = mvcResult.response.status

        expect:
            status == HttpStatus.BAD_REQUEST.value()
            jsonResult.errors.messages[0].message == "checklist.definition.invalid.task"
            jsonResult.errors.messages[0].code == HttpStatus.BAD_REQUEST.value()

            jsonResult.uri == "/rs/saleables/task-definitions/9999/checklist-definitions"
    }

    @Unroll
    def "Should delete a checklist definitions"() {

        def mvcResult = mockMvc.perform(delete("/rs/saleables/task-definitions/checklist-definitions/6")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def result = mockMvc.perform(get("/rs/saleables/task-definitions/3/checklist-definitions")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse().getContentAsString()

        def checklists = new JsonSlurper().parseText(result)

        def status = mvcResult.response.status

        expect:
            status == HttpStatus.OK.value()
            checklists.items.size == 1
    }

}
