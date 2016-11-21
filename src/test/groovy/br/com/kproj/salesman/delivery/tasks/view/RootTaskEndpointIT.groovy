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
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Stepwise
@ClassReference(RootTaskEndpoint)
class RootTaskEndpointIT extends AbstractIntegrationTest {

    private static final String TASKS_CREATE = "/delivery/tasks/task-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(TASKS_CREATE)
    }


    @Unroll
    def "Should create a specialization root of task"() {
        given:
            def createRootTaskSpecialization = "/rs/deliveries/tasks/root-tasks"
            def createTask = "/rs/deliveries/tasks"
        when: "A new task is created "
            def newTask = new JsonSlurper().parseText(scenery("Criando uma nova task(roottask) com todos os dados validos").getJson())

            def mvcResult = mockMvc.perform(post(createTask).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newTask))).andReturn()

            def taskCreated = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
        and: "A RootTask specialization is created"

            def result = mockMvc.perform(post(createRootTaskSpecialization).contentType(MediaType.APPLICATION_JSON)
                .content("{\"task\":{\"id\":$taskCreated.item.id}}")).andReturn()

            def rootTaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a new root task"
            rootTaskCreated.item.id == taskCreated.item.id
            rootTaskCreated.item.task.id == taskCreated.item.id
            rootTaskCreated.item.task.title == taskCreated.item.title
            rootTaskCreated.item.task.description == taskCreated.item.description
            rootTaskCreated.item.task.deadline == "2018-09-05T00:00:00.000+0000"
            rootTaskCreated.item.task.links.size == 1
            rootTaskCreated.item.task.links[0].href == "/deliveries/3"
            rootTaskCreated.item.task.links[0].rel == "of-delivery"

            rootTaskCreated.item.links.size == 1
            rootTaskCreated.item.links[0].href == "/deliveries/tasks/root-tasks/$rootTaskCreated.item.id/subtasks"
            rootTaskCreated.item.links[0].rel == "children"

            rootTaskCreated.uri == "/rs/deliveries/tasks/root-tasks"

            result.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a specialization root task when invalid task informed"() {
        given:
            def createRootTaskSpecialization = "/rs/deliveries/tasks/root-tasks"
        when: "A new root task is created "

            def result = mockMvc.perform(post(createRootTaskSpecialization)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"task\":{\"id\": 6666}}"))
                    .andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a error"
            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks"

            result.response.status == HttpStatus.BAD_REQUEST.value
    }


    @Unroll
    def "Should not create a specialization root task when task already with specialization roottask"() {
        given:
            def createSubtaskSpecialization = "/rs/deliveries/tasks/root-tasks"
        when: "try to create a root task"

            def result = mockMvc.perform(post(createSubtaskSpecialization).contentType(MediaType.APPLICATION_JSON)
                    .content("{\"task\":{\"id\":1}}")).andReturn()

            def subtaskCreated = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a error"
            subtaskCreated.uri == "/rs/deliveries/tasks/root-tasks"
            result.response.status == HttpStatus.BAD_REQUEST.value
    }
}
