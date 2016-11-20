package br.com.kproj.salesman.delivery.tasks.view

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
import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@Stepwise
@ClassReference(TaskEndpoint)
class TaskEndpointIT extends AbstractIntegrationTest {

    private static final String TASKS_CREATE = "/delivery/tasks/task-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(TASKS_CREATE)
    }

    @Unroll
    def "Should create a task with all data"() {
        given:
            def uri = "/rs/deliveries/tasks"
            def newTask = new JsonSlurper().parseText(scenery("Criando uma nova task com todos os dados validos").getJson())
        when:

            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newTask))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new task associated with delivery 3"
            jsonResult.item.id != null
            jsonResult.item.title == newTask.title
            jsonResult.item.description == newTask.description
            jsonResult.item.deadline != null
            jsonResult.item.status == "WAITING"

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a task without title"() {
        given:
            def uri = "/rs/deliveries/tasks"
            def newTask = new JsonSlurper().parseText(scenery("Task sem o titulo").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newTask))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a task with deadline less than current date"() {
        given:
            def uri = "/rs/deliveries/tasks"
            def newTask = new JsonSlurper().parseText(scenery("Task com a deadline menor que a data atual").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newTask))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a task with invalid delivery"() {
        given:
            def uri = "/rs/deliveries/tasks"
            def newTask = new JsonSlurper().parseText(scenery("Task com delivery invalido").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newTask))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should update title of task"() {
        given:
            def uri = "/rs/deliveries/tasks/8"
            def taskWithNewTitle = new JsonSlurper().parseText(scenery("Task com um novo titulo para atualizacao").getJson())
        when:

            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(taskWithNewTitle))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a task with title changed"
            jsonResult.item.id == 8
            jsonResult.item.title == taskWithNewTitle.title

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update status of task"() {
        given:
            def uri = "/rs/deliveries/tasks/8"
            def taskWithNewStatus = new JsonSlurper().parseText(scenery("Task com novo status para atualizacao").getJson())
        when:

            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(taskWithNewStatus))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a task with status changed"
            jsonResult.item.id == 8
            jsonResult.item.status == taskWithNewStatus.status

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update description of task"() {
        given:
            def uri = "/rs/deliveries/tasks/8"
            def taskWithNewDescription = new JsonSlurper().parseText(scenery("Task com nova descricao para atualizacao").getJson())
        when:

            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(taskWithNewDescription))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a task with description changed"
            jsonResult.item.id == 8
            jsonResult.item.description == taskWithNewDescription.description

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update all fields of task"() {
        given:
            def uri = "/rs/deliveries/tasks/8"
            def taskWithAllFieldsUpdated = new JsonSlurper().parseText(scenery("Task com todos os dados alterados para atualizacao").getJson())
        when:

            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(taskWithAllFieldsUpdated))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a task with all data changed"
            jsonResult.item.id == 8
            jsonResult.item.description == taskWithAllFieldsUpdated.description
            jsonResult.item.title == taskWithAllFieldsUpdated.title
            jsonResult.item.status == taskWithAllFieldsUpdated.status
            jsonResult.item.deadline == "2040-04-05T00:00:00.000+0000"

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should get a task by ID"() {
        given:
            def uri = "/rs/deliveries/tasks/1"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a task by ID"
            jsonResult.item.id == 1
            jsonResult.item.title == "title"
            jsonResult.item.description == "description"
            jsonResult.item.deadline != null
            jsonResult.item.status == "WAITING"

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

}
