package br.com.kproj.salesman.delivery.tasks.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ClassReference(SubscriberEndpoint)
class SubscriberEndpointIT extends AbstractIntegrationTest {


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    def "Should find all subscriber of an task"() {
        given:
            def uri = "/rs/deliveries/tasks/6/task-subscribers"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "Should return 2 subscribers"
            jsonResult.items.size == 2
            jsonResult.items[0].id == 3
            jsonResult.items[0].links.size == 2
            jsonResult.items[0].links.find{it.rel == "has-task"}.href == "/deliveries/tasks/6"
            jsonResult.items[0].links.find{it.rel == "has-user"}.href == "/users/1"

            jsonResult.items[1].id == 4
            jsonResult.items[1].links.size == 2
            jsonResult.items[1].links.find{it.rel == "has-task"}.href == "/deliveries/tasks/6"
            jsonResult.items[1].links.find{it.rel == "has-user"}.href == "/users/2"

            jsonResult.uri == uri
            status == HttpStatus.OK.value
    }

    @Unroll
    def "Should create an subscriber to task"() {
        given:
            def uri = "/rs/deliveries/tasks/1/task-subscribers"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                    .contentType(MediaType.APPLICATION_JSON).content('''{"user":{"id":2}}''')).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "Should return a subscribe created"
            jsonResult.item.id != null
            jsonResult.item.links.size == 2
            jsonResult.item.links.find{it.rel == "has-task"}.href == "/deliveries/tasks/1"
            jsonResult.item.links.find{it.rel == "has-user"}.href == "/users/2"

            jsonResult.uri == uri
            status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should delete an subscriber"() {
        given: "a subscriber id from task with id 7"
            def uri = "/rs/deliveries/tasks/task-subscribers/6"
            def uriSubscribers = "/rs/deliveries/tasks/7/task-subscribers"
        when:
            def mvcResult = mockMvc.perform(delete(uri)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn()
        and: "get subscribers for the task 7"
            def subscribersNotFound = mockMvc.perform(get(uriSubscribers).contentType(MediaType.APPLICATION_JSON))
                .andReturn().response.status

        then: "Should return status 200"
            mvcResult.response.status == HttpStatus.OK.value
        and: "subscribers not found for the task"
            subscribersNotFound == HttpStatus.NOT_FOUND.value
    }
}
