package br.com.kproj.salesman.accounts.addresses.view

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

@ClassReference(AddressesEndpoint)
class AddressesEndpointIT extends AbstractIntegrationTest {


    private static final String ADDRESSES = "/accounts/addresses/addresses.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ADDRESSES)
    }

    @Unroll
    def "Should find all addresses by customer using paginated"() {
        given:
            def uri = "/rs/customers/1/addresses?page=0&size=1"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de enderecos de um cliente paginado").json)
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 1
            jsonResult.totalItems == 2
            jsonResult.pageSize == 1

            jsonResult.items[0] == jsonExpected.items[0]
    }

    @Unroll
    def "Should find one address by ID"() {
        given:
            def uri = "/rs/customers/addresses/3"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonExpected = new JsonSlurper().parseText(scenery("Endereco buscando pelo ID").json)
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item == jsonExpected.item
            jsonResult.uri == uri
    }

    @Unroll
    def "Should create a address with all data"() {
        given:
            def uri = "/rs/customers/3/addresses"
            def newAddress = new JsonSlurper().parseText(scenery("should create a address with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newAddress))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new address for customer 3"
            jsonResult.item.id != null
            jsonResult.item.street == newAddress.street
            jsonResult.item.city == newAddress.city
            jsonResult.item.state == newAddress.state
            jsonResult.item.zipCode == newAddress.zipCode
            jsonResult.item.country == newAddress.country
            jsonResult.item.type == newAddress.type

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-customer"}.href == "/customers/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should update customer"() {
        given:
            def uri = "/rs/customers/addresses/4"
            def addressWithDataToUpdate = new JsonSlurper().parseText(scenery("should update a address").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(addressWithDataToUpdate))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a address with update data"
            jsonResult.item.id == 4
            jsonResult.item.street == addressWithDataToUpdate.street
            jsonResult.item.city == addressWithDataToUpdate.city
            jsonResult.item.state == addressWithDataToUpdate.state
            jsonResult.item.zipCode == addressWithDataToUpdate.zipCode
            jsonResult.item.country == addressWithDataToUpdate.country
            jsonResult.item.type == addressWithDataToUpdate.type

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update only fields address not required"() {
        given:
            def uri = "/rs/customers/addresses/5"
            def addressWithDataToUpdate = new JsonSlurper().parseText(scenery("should update only fields address not required").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(addressWithDataToUpdate))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a address with update data"
            jsonResult.item.id == 5
            jsonResult.item.street == "Rua XXXX"
            jsonResult.item.city == addressWithDataToUpdate.city
            jsonResult.item.state == addressWithDataToUpdate.state
            jsonResult.item.zipCode == addressWithDataToUpdate.zipCode
            jsonResult.item.country == addressWithDataToUpdate.country
            jsonResult.item.type == "BILLING"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

}
