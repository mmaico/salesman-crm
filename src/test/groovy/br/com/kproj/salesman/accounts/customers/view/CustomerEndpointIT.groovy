package br.com.kproj.salesman.accounts.customers.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ClassReference(CustomerEndpoint)
class CustomerEndpointIT extends AbstractIntegrationTest {


    private static final String CUSTOMERS = "/accounts/customers/customers.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CUSTOMERS)
    }

    @Unroll
    def "Should find all customers paginated"() {
        given:
            def uri = "/rs/customers?page=0&size=2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de customers cadastrado no sistema paginado").json)
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 2
            jsonResult.totalItems == 3
            jsonResult.pageSize == 2

            jsonResult.items[0].links.sort{it.rel}
            jsonResult.items[0] == jsonExpected.items[0]

            jsonResult.items[1].links.sort{it.rel}
            jsonResult.items[1] == jsonExpected.items[1]
    }

    @Unroll
    def "Should find one customer by ID"() {
        given:
            def uri = "/rs/customers/2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonExpected = new JsonSlurper().parseText(scenery("Customer buscando pelo ID").json)
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item.links.sort{it.rel}
            jsonResult.item == jsonExpected.item
            jsonResult.uri == uri
    }

    @Unroll
    def "Should create a customer with all data"() {
        given:
            def uri = "/rs/customers"
            def newCustomer = new JsonSlurper().parseText(scenery("should create a customer with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newCustomer))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new customer"
            jsonResult.item.id != null
            jsonResult.item.name == newCustomer.name
            jsonResult.item.description == newCustomer.description
            jsonResult.item.site == newCustomer.site

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should update customer"() {
        given:
            def uri = "/rs/customers/3"
            def customerWithDataToUpdate = new JsonSlurper().parseText(scenery("should update customer").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(customerWithDataToUpdate))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a customer with update data"
            jsonResult.item.id == 3
            jsonResult.item.name == customerWithDataToUpdate.name
            jsonResult.item.description == customerWithDataToUpdate.description
            jsonResult.item.site == customerWithDataToUpdate.site

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

}
