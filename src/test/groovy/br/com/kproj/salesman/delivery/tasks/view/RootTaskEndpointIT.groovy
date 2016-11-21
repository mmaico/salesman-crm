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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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

    @Unroll
    def "Should list all root task registered of a delivery"() {
        given:
            def uri = "/rs/deliveries/3/tasks/root-tasks"
        when: "list a root tasks by delivery 3"

            def result = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()

            def rootTaskList = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should all root tasks of a delivery and status 200"
            rootTaskList.items.size == 2
            rootTaskList.items[0].id == 1
            rootTaskList.items[0].task.id == 1
            rootTaskList.items[0].task.title == "title"
            rootTaskList.items[0].task.description == "description"
            rootTaskList.items[0].task.deadline == "2016-02-19T02:00:00.000+0000"
            rootTaskList.items[0].task.links.size == 1
            rootTaskList.items[0].task.links.find{it.rel == "of-delivery"}.href == "/deliveries/3"
            rootTaskList.items[0].links.find{it.rel == "children"}.href == "/deliveries/tasks/root-tasks/1/subtasks"

            rootTaskList.items[1].id == 2
            rootTaskList.items[1].task.id == 2
            rootTaskList.items[1].task.title == "title"
            rootTaskList.items[1].task.description == "description"
            rootTaskList.items[1].task.deadline == "2016-02-18T02:00:00.000+0000"
            rootTaskList.items[1].task.links.size == 1
            rootTaskList.items[1].task.links.find{it.rel == "of-delivery"}.href == "/deliveries/3"
            rootTaskList.items[1].links.find{it.rel == "children"}.href == "/deliveries/tasks/root-tasks/2/subtasks"

            rootTaskList.uri == uri
            result.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find a root task by ID"() {
        given:
            def uri = "/rs/deliveries/tasks/root-tasks/2"
        when: "find a root tasks by ID"
            def result = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def rootTask = new JsonSlurper().parseText(result.response.contentAsString)

        then: "Should return a root task and status 200"
            rootTask.item.id == 2
            rootTask.item.task.id == 2
            rootTask.item.task.title == "title"
            rootTask.item.task.description == "description"
            rootTask.item.task.deadline == "2016-02-18T02:00:00.000+0000"
            rootTask.item.task.links.size == 1
            rootTask.item.task.links.find{it.rel == "of-delivery"}.href == "/deliveries/3"
            rootTask.item.links.find{it.rel == "children"}.href == "/deliveries/tasks/root-tasks/2/subtasks"

            rootTask.uri == uri
            result.response.status == HttpStatus.OK.value
    }
}
