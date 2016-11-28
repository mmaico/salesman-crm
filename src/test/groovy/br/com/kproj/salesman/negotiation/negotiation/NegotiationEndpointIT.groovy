package br.com.kproj.salesman.negotiation.negotiation

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.negotiation.negotiation.view.NegotiationEndpoint
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

@ClassReference(NegotiationEndpoint)
class NegotiationEndpointIT extends AbstractIntegrationTest {


    private static final String NEGOTIATIONS = "/negotiation/negotiation/negotiations.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(NEGOTIATIONS)
    }

    @Unroll
    def "Should find all negotiations paginated"() {
        given:
            def uri = "/rs/customers/negotiations?page=0&size=2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de negociacoes cadastrado no sistema paginado").json)
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 2
            jsonResult.totalItems == 4
            jsonResult.pageSize == 2

            jsonResult.items[0].links.sort{it.rel}
            jsonResult.items[0] == jsonExpected.items[0]

            jsonResult.items[1].links.sort{it.rel}
            jsonResult.items[1] == jsonExpected.items[1]
    }

    @Unroll
    def "Should find one customer by ID"() {
        given:
            def uri = "/rs/customers/negotiations/2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonExpected = new JsonSlurper().parseText(scenery("Negotiation buscando pelo ID").json)
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item.links.sort{it.rel}
            jsonResult.item == jsonExpected.item
            jsonResult.uri == uri
    }

    @Unroll
    def "Should return 404 when negotiation not exist"() {
        given:
            def uri = "/rs/customers/negotiations/6666"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.NOT_FOUND.value
    }

    @Unroll
    def "Should create a negotiation with all data"() {
        given:
            def uri = "/rs/customers/3/negotiations"
            def newNegotiation = new JsonSlurper().parseText(scenery("should create a negotiation with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new negotiation"
            jsonResult.item.id != null
            jsonResult.item.introduction == newNegotiation.introduction
            jsonResult.item.careOf == newNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2018-09-05T00:00:00.000+0000"
            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/3"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/4"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a negotiation with invalid customer"() {
        given:
            def uri = "/rs/customers/6666/negotiations"
            def newNegotiation = new JsonSlurper().parseText(scenery("should create a negotiation with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newNegotiation))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should create a negotiation only required data"() {
        given:
            def uri = "/rs/customers/3/negotiations"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content('''{"introduction": ""}''')).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new negotiation"
            jsonResult.item.id != null
            jsonResult.item.introduction == null
            jsonResult.item.careOf == null
            jsonResult.item.deliveryForeCast == null
            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }
//
//    @Unroll
//    def "Should update customer"() {
//        given:
//            def uri = "/rs/customers/3"
//            def customerWithDataToUpdate = new JsonSlurper().parseText(scenery("should update customer").getJson())
//        when:
//            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
//                .content(toJson(customerWithDataToUpdate))).andReturn()
//
//            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
//
//        then: "Should return a customer with update data"
//            jsonResult.item.id == 3
//            jsonResult.item.name == customerWithDataToUpdate.name
//            jsonResult.item.description == customerWithDataToUpdate.description
//            jsonResult.item.site == customerWithDataToUpdate.site
//
//            jsonResult.uri == uri
//            mvcResult.response.status == HttpStatus.OK.value
//    }

}
