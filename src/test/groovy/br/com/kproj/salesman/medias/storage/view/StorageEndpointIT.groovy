package br.com.kproj.salesman.medias.storage.view

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(NegotiationEndpoint)
class StorageEndpointIT extends AbstractIntegrationTest {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    "Should find all storages"() {
        given:
            def uri = "/rs/storages"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 3
            jsonResult.totalItems == 3
            jsonResult.pageSize == 100

            jsonResult.items.find{it.id == 1}.name == "avatar"
            jsonResult.items.find{it.id == 2}.name == "timeline"
            jsonResult.items.find{it.id == 3}.name == "timeline-company"

    }

    @Unroll
    "Should find one storage by ID"() {
        given:
            def uri = "/rs/storages/3"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item.id == 3
            jsonResult.item.name == "timeline-company"
            jsonResult.uri == uri
    }

    @Unroll
    "Should return 404 when storage not found"() {
        given:
            def uri = "/rs/storages/6666"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.NOT_FOUND.value
    }

    @Unroll
    "Should create a storages with name"() {
        given:
            def uri = "/rs/storages"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content('''{"name": "storage-timeline-task"}''')).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new storage"
            jsonResult.item.id != null
            jsonResult.item.name == "storage-timeline-task"
            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    "Should not create a storages with space"() {
        given:
            def uri = "/rs/storages"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content('''{"name": "storage timeline task"}''')).andReturn()
        then: "Should return a new storage"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value()
    }

}
