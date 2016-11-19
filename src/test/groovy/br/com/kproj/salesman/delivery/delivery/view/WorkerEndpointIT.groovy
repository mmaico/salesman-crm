package br.com.kproj.salesman.delivery.delivery.view

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete

@Stepwise
@ClassReference(WorkerEndpoint)
class WorkerEndpointIT extends AbstractIntegrationTest {

    private static final String CHECKLIST_LIST = "/products_catalog/task_definition/checklist/checklist-definitions.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CHECKLIST_LIST)
    }

    @Unroll
    def "Should find all workers by delivery"() {
        given:
            def uri = "/rs/deliveries/2/workers"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "Should return 3 workers association with delivery"
            jsonResult.items.size == 3
            jsonResult.items[0].id == 1
            jsonResult.items[0].links.size == 2
            jsonResult.items[0].links.find{it.rel == "user"}.href == "/users/2"
            jsonResult.items[0].links.find{it.rel == "of-delivery"}.href == "/deliveries/2"

            jsonResult.items[1].id == 2
            jsonResult.items[1].links.size == 2
            jsonResult.items[1].links.find{it.rel == "user"}.href == "/users/3"
            jsonResult.items[1].links.find{it.rel == "of-delivery"}.href == "/deliveries/2"

            jsonResult.uri == "/rs/deliveries/2/workers"

            status == HttpStatus.OK.value
    }

    @Unroll
    def "Should create a worker for delivery"() {
        given:
            def jsonWorker = '''{"user": {"id":2}}'''
            def mvcResult = mockMvc.perform(post("/rs/deliveries/2/workers")
                .content(jsonWorker)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        when:
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
            def status = mvcResult.response.status

        then: "Should a worker created and status 201"
            jsonResult.item.id != null
            jsonResult.item.links.size == 2
            jsonResult.item.links.find{it.rel == "user"}.href == "/users/2"
            jsonResult.item.links.find{it.rel == "of-delivery"}.href == "/deliveries/2"

            status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should delete a worker by ID"() {
        given:
            def uri = "/rs/deliveries/workers/2"
            def urlAllWorkers = "/rs/deliveries/2/workers"
        when: "A worker is exluded "
            def mvcResult = mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def status = mvcResult.response.status

        and: "a request make to return all workers by delivery 2"
            def result = new JsonSlurper().parseText(mockMvc.perform(get(urlAllWorkers)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()
                    .getResponse().getContentAsString())

        then: "Should return 2 worker association with delivery 2"
            result.items.size == 2
            status == HttpStatus.OK.value
    }

}
