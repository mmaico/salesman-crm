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
    def "Should find deliveries using filters"() {
        given:
            def uri = "/rs/deliveries?filter=has-workers.size.gg(0)"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def status = mvcResult.response.status

        then: "Should return 2 workers association with delivery"
            status == HttpStatus.OK.value
    }

}
