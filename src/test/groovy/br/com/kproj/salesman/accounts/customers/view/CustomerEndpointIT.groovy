package br.com.kproj.salesman.accounts.customers.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@ClassReference(CustomerEndpoint)
class CustomerEndpointIT extends AbstractIntegrationTest {


    private static final String CUSTOMERS = "/products_catalog/task_definition/checklist/checklist-definitions.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CUSTOMERS)
    }

    def "teste one" () {
        when:
            println("teste when one")

        then:
            println("teste one")
    }

    def "teste two" () {
        when:
            println("teste when")

        then:
            println("teste two")
    }
}
