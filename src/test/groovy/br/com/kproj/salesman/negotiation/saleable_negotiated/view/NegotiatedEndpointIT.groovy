package br.com.kproj.salesman.negotiation.saleable_negotiated.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(NegotiatedEndpoint)
class NegotiatedEndpointIT extends AbstractIntegrationTest {


    private static final String NEGOTIATED = "/negotiation/saleable_negotiated/negotiateds.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(NEGOTIATED)
    }

    @Unroll
    def "Should find all negotiated item by negotiation"() {
        given:
            def uri = "/rs/customers/negotiations/11/negotiated-items"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de items negociados pelo ID da negociacao").json)
        then:
            jsonResult.uri == uri
            jsonResult.items[0].id == jsonExpected.items[0].id
            jsonResult.items[0].price == jsonExpected.items[0].price
            jsonResult.items[0].originalPrice == jsonExpected.items[0].originalPrice
            jsonResult.items[0].quantity == jsonExpected.items[0].quantity
            jsonResult.items[0].saleableItems[0].id == jsonExpected.items[0].saleableItems[0].id

            jsonResult.items[0].links[0].href == jsonExpected.items[0].links[0].href
            jsonResult.items[0].links[0].rel == jsonExpected.items[0].links[0].rel

            jsonResult.items[0].saleableItems[0].links.sort{it.rel}
            jsonResult.items[0].saleableItems[0].links == jsonResult.items[0].saleableItems[0].links

            jsonResult.items[0].saleableItems[1].links.sort{it.rel}
            jsonResult.items[0].saleableItems[1].links == jsonResult.items[0].saleableItems[1].links

            jsonResult.items[0].saleableItems[2].links.sort{it.rel}
            jsonResult.items[0].saleableItems[2].links == jsonResult.items[0].saleableItems[2].links

            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find a negotiated by ID"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/12"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Negotiated buscando pelo ID").json)
        then:
            jsonResult.uri == uri
            jsonResult.item.id == jsonExpected.item.id
            jsonResult.item.price == jsonExpected.item.price
            jsonResult.item.originalPrice == jsonExpected.item.originalPrice
            jsonResult.item.quantity == jsonExpected.item.quantity
            jsonResult.item.links[0] == jsonExpected.item.links[0]

            jsonResult.item.saleableItems[0].id == jsonExpected.item.saleableItems[0].id
            jsonResult.item.saleableItems[0].links.sort{it.rel}
            jsonResult.item.saleableItems[0].links == jsonExpected.item.saleableItems[0].links

            jsonResult.item.saleableItems[1].id == jsonExpected.item.saleableItems[1].id
            jsonResult.item.saleableItems[1].links.sort{it.rel}
            jsonResult.item.saleableItems[1].links == jsonExpected.item.saleableItems[1].links

            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should create a negotiated with all data for a package saleable"() {
        given:
            def uri = "/rs/customers/negotiations/6/negotiated-items?forSaleable=7"
            def newNegotiated = new JsonSlurper().parseText(scenery("Should create a negotiated with all data for a package saleable").getJson())
            def jsonExpected = new JsonSlurper().parseText(scenery("data expected on create a negotiated item for a negotiation").getJson())

        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newNegotiated))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
        then: "Should return a new negotiated item"
            jsonResult.uri == uri
            jsonResult.item.id != null
            jsonResult.item.price == jsonExpected.item.price
            jsonResult.item.originalPrice == jsonExpected.item.originalPrice
            jsonResult.item.quantity == jsonExpected.item.quantity
            jsonResult.item.links[0] == jsonExpected.item.links[0]

            jsonResult.item.saleableItems[0].id == jsonExpected.item.saleableItems[0].id
            jsonResult.item.saleableItems[0].links.sort{it.rel}
            jsonResult.item.saleableItems[0].links == jsonExpected.item.saleableItems[0].links

            jsonResult.item.saleableItems[1].id == jsonExpected.item.saleableItems[1].id
            jsonResult.item.saleableItems[1].links.sort{it.rel}
            jsonResult.item.saleableItems[1].links == jsonExpected.item.saleableItems[1].links

            mvcResult.response.status == HttpStatus.CREATED.value
    }

}
