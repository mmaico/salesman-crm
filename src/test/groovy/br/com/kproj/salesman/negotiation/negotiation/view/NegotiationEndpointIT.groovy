package br.com.kproj.salesman.negotiation.negotiation.view

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

@ClassReference(NegotiationEndpoint)
class NegotiationEndpointIT extends AbstractIntegrationTest {


    private static final String NEGOTIATIONS = "/negotiation/negotiation/negotiations.json"


    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(NEGOTIATIONS)
    }

    @Unroll
    "Should find all negotiations paginated"() {
        given:
            def uri = "/rs/customers/negotiations?page=0&size=2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de negociacoes cadastrado no sistema paginado").json)
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 2
            jsonResult.totalItems == 11
            jsonResult.pageSize == 2

            jsonResult.items[0].links.sort{it.rel}
            jsonResult.items[0] == jsonExpected.items[0]

            jsonResult.items[1].links.sort{it.rel}
            jsonResult.items[1] == jsonExpected.items[1]
    }

    @Unroll
    "Should find one customer by ID"() {
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
    "Should return 404 when negotiation not exist"() {
        given:
            def uri = "/rs/customers/negotiations/6666"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.NOT_FOUND.value
    }

    @Unroll
    "Should create a negotiation with all data"() {
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
    "Should not create a negotiation with invalid customer"() {
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
    "Should create a negotiation only required data"() {
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

    @Unroll
    "Should update a negotiation with all data"() {
        given:
            def uri = "/rs/customers/negotiations/5"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 5
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == dataToUpdateNegotiation.ammountPayable
            jsonResult.item.temperature == dataToUpdateNegotiation.temperature

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/2"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/4"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller"() {
        given:
            def uri = "/rs/customers/negotiations/6"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation without seller").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 6
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == dataToUpdateNegotiation.ammountPayable
            jsonResult.item.temperature == dataToUpdateNegotiation.temperature

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/2"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/4"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller, customer"() {
        given:
            def uri = "/rs/customers/negotiations/7"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation without seller, customer").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 7
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == dataToUpdateNegotiation.ammountPayable
            jsonResult.item.temperature == dataToUpdateNegotiation.temperature

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/1"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/4"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller, customer, region"() {
        given:
            def uri = "/rs/customers/negotiations/8"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation without seller, customer, region").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 8
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == dataToUpdateNegotiation.ammountPayable
            jsonResult.item.temperature == dataToUpdateNegotiation.temperature

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/1"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/1"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller, customer, region, temperature"() {
        given:
            def uri = "/rs/customers/negotiations/9"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation without seller, customer, region, temperature").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 9
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == dataToUpdateNegotiation.ammountPayable
            jsonResult.item.temperature == "COLD"

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/1"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/1"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller, customer, region, temperature, ammountPayable"() {
        given:
            def uri = "/rs/customers/negotiations/10"
            def dataToUpdateNegotiation = new JsonSlurper().parseText(scenery("should update a negotiation without seller, customer, region, temperature, ammountPayable").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 10
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == dataToUpdateNegotiation.discount
            jsonResult.item.ammountPayable == 30.00
            jsonResult.item.temperature == "COLD"

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/1"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/1"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should update a negotiation without seller, customer, region, temperature, ammountPayable, discount"() {
        given:
            def uri = "/rs/customers/negotiations/11"
            def dataToUpdateNegotiation = new JsonSlurper()
                .parseText(scenery("should update a negotiation without seller, customer, region, temperature, ammountPayable, discount").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(dataToUpdateNegotiation))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a negotiation updated"
            jsonResult.item.id == 11
            jsonResult.item.introduction ==  dataToUpdateNegotiation.introduction
            jsonResult.item.careOf ==  dataToUpdateNegotiation.careOf
            jsonResult.item.deliveryForeCast == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.discount == 11.00
            jsonResult.item.ammountPayable == 30.00
            jsonResult.item.temperature == "COLD"

            jsonResult.item.links.find{it.rel == "customer"}.href == "/customers/1"
            jsonResult.item.links.find{it.rel == "region"}.href == "/regions/1"
            jsonResult.item.links.find{it.rel == "seller"}.href == "/users/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }


}
