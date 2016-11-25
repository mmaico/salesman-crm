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
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(SubtaskEndpoint)
class SubtaskEndpointIT extends AbstractIntegrationTest {

    private static final String TASKS_CREATE = "/delivery/tasks/task-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(TASKS_CREATE)
    }


    @Unroll
    def "Should create a specialization subtask of task"() {
        given:
            def createSubtaskSpecialization = "/rs/deliveries/tasks/root-tasks/2/subtasks"
            def createTask = "/rs/deliveries/3/tasks"
        when: "A new task is created "
            def newTask = new JsonSlurper().parseText(scenery("Criando uma nova task com todos os dados validos").getJson())

            def mvcResult = mockMvc.perform(post(createTask).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newTask))).andReturn()

            def taskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
        and: "A subtask specialization is created"

            def result = mockMvc.perform(post(createSubtaskSpecialization).contentType(MediaType.APPLICATION_JSON)
                .content("{\"task\":{\"id\":$taskCreated.item.id}}")).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a new subtask of the root task 2"
            subtaskCreated.item.id == taskCreated.item.id
            subtaskCreated.item.task.id == taskCreated.item.id
            subtaskCreated.item.task.title == taskCreated.item.title
            subtaskCreated.item.task.description == taskCreated.item.description
            subtaskCreated.item.task.deadline == "2018-09-05T00:00:00.000+0000"
            subtaskCreated.item.task.links.size == 1
            subtaskCreated.item.task.links[0].href == "/deliveries/3"
            subtaskCreated.item.task.links[0].rel == "of-delivery"

            subtaskCreated.item.links.size == 1
            subtaskCreated.item.links[0].href == "/deliveries/tasks/root-tasks/2"
            subtaskCreated.item.links[0].rel == "child-of"

            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks/2/subtasks"

            result.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a specialization subtask when task not informed"() {
        given:
            def createSubtaskSpecialization = "/rs/deliveries/tasks/root-tasks/2/subtasks"
        when: "A new sub task is created "

            def result = mockMvc.perform(post(createSubtaskSpecialization)
                    .content("{}")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a error"
            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks/2/subtasks"

            result.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a specialization subtask when invalid parent"() {
        given:
            def createSubtaskSpecialization = "/rs/deliveries/tasks/root-tasks/6666/subtasks"
            def createTask = "/rs/deliveries/3/tasks"
        when: "A new sub task is created"
            def newTask = new JsonSlurper().parseText(scenery("Criando uma nova task com todos os dados validos").getJson())

            def mvcResult = mockMvc.perform(post(createTask).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newTask))).andReturn()

            def taskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

            def result = mockMvc.perform(post(createSubtaskSpecialization).contentType(MediaType.APPLICATION_JSON)
                .content("{\"task\":{\"id\":$taskCreated.item.id}}")).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a error"
            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks/6666/subtasks"

            result.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a specialization subtask when task already with specialization subtask"() {
        given:
            def createSubtaskSpecialization = "/rs/deliveries/tasks/root-tasks/2/subtasks"
        when: "A new sub task is created "

            def result = mockMvc.perform(post(createSubtaskSpecialization).contentType(MediaType.APPLICATION_JSON)
                    .content("{\"task\":{\"id\":4}}")).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a error"
            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks/2/subtasks"
            result.response.status == HttpStatus.BAD_REQUEST.value
    }
}
