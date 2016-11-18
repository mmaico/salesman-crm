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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@Stepwise
@ClassReference(DeliveryEndpoint)
class DeliveryEndpointIT extends AbstractIntegrationTest {

    private static final String CHECKLIST_LIST = "/products_catalog/task_definition/checklist/checklist-definitions.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CHECKLIST_LIST)
    }

    @Unroll
    def "Should find deliveries without workers"() {
        given:
            def uri = "/rs/deliveries?filter=has-workers.size.eq(0)"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def status = mvcResult.response.status
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)

        then: "Should return 1 delivery without workers"
            status == HttpStatus.OK.value
            resultJson.totalItems == 1
            resultJson.items.size == 1
            resultJson.items[0].id == 3
            resultJson.items[0].deliveryForecast != null
            resultJson.items[0].links.size == 1
            resultJson.items[0].links[0].href == "/sales-orders/2"
            resultJson.items[0].links[0].rel == "of-salesOrder"
    }

    @Unroll
    def "Should find deliveries with workers"() {
        given:
        def uri = "/rs/deliveries?filter=has-workers.size.gt(0)"
        when:
        def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        def status = mvcResult.response.status
        def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)

        then: "Should return 2 deliveries"
            status == HttpStatus.OK.value
            resultJson.totalItems == 2
            resultJson.items.size == 2
            resultJson.items[0].id == 1
            resultJson.items[1].id == 2
    }

    @Unroll
    def "Should find deliveries with workers less than 4"() {
        given:
        def uri = "/rs/deliveries?filter=has-workers.size.lt(4)"
        when:
        def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        def status = mvcResult.response.status
        def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)

        then: "Should return 2 deliveries"
        status == HttpStatus.OK.value
        resultJson.totalItems == 3
        resultJson.items.size == 3
    }

}
