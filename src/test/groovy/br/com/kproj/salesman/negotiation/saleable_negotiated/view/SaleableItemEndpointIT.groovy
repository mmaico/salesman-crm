package br.com.kproj.salesman.negotiation.saleable_negotiated.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(SaleableItemEndpoint)
class SaleableItemEndpointIT extends AbstractIntegrationTest {


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    def "Should delete a saleables-items by ID"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/saleables-items/7"
        when:
            def mvcResult = mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should create a saleables-items to negotiated"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/6/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                    .content('''{"saleable": {"id": 1}, "usedPackage": {"id": 7}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
            def saleableItem = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then:
            saleableItem.item.id != null
            saleableItem.item.links.size == 3
            saleableItem.item.links.find{it.rel == "saleable"}.href == "/saleables/1"
            saleableItem.item.links.find{it.rel == "used-package"}.href == "/saleables/packages/7"
            saleableItem.item.links.find{it.rel == "negotiated"}.href == "/customers/negotiations/negotiated-items/6"

            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a saleables-items when invalid saleable"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/6/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 6666}, "usedPackage": {"id": 7}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when invalid package"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/6/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 1}, "usedPackage": {"id": 6666}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when invalid negotiated"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/6666/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 1}, "usedPackage": {"id": 7}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when the package informed is not a package"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/6/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 1}, "usedPackage": {"id": 3}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when negotiated already has the product"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/9/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 2}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when negotiated already has the product and package associated"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/13/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 2}, "usedPackage": {"id":7}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a saleables-items when saleable does not belong to the package informed"() {
        given:
            def uri = "/rs/customers/negotiations/negotiated-items/13/saleables-items"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content('''{"saleable": {"id": 5}, "usedPackage": {"id":7}}''').contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

}
