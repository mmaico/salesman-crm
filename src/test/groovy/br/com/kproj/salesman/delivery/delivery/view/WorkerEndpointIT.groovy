package br.com.kproj.salesman.delivery.delivery.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Stepwise
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

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

        def mvcResult = mockMvc.perform(get("/rs/deliveries/2/workers")
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        //def jsonExpected = scenery("Lista de todos as checklist definitions de uma task definition").json

        //def jsonResult = mvcResult.response.getContentAsString()
        def status = mvcResult.response.status

        expect:
        //jsonResult == jsonExpected
            status == HttpStatus.OK.value
    }

}
