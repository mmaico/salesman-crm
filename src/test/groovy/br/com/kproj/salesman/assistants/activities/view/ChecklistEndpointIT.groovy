package br.com.kproj.salesman.assistants.activities.view

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@ClassReference(ChecklistEndpoint)
class ChecklistEndpointIT extends AbstractIntegrationTest {

    private static final String CHECKLISTS = "/delivery/tasks/checklists.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CHECKLISTS)
    }

    @Unroll
    def "Should create a checklist of a person activity"() {
        given:
            def uri = "/rs/users/personal-activities/1/activities-checklists"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content('''{"name": "checklist 01"}''')).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new checklist associated with person activity"
            jsonResult.item.id != null
            jsonResult.item.name == "checklist 01"
            jsonResult.item.done == Boolean.FALSE

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-activity"}.href == "/users/personal-activities/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a checklist of activity when not have name"() {
        given:
            def uri = "/rs/users/personal-activities/1/activities-checklists"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content('''{}''')).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a checklist of activity when invalid activity"() {
        given:
            def uri = "/rs/users/personal-activities/9999/activities-checklists"
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content('''{"name": "checklist 9999"}''')).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should return all checklist of activity"() {
        given:
            def uri = "/rs/users/personal-activities/2/activities-checklists"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return all checklist of activity"
            jsonResult.items.size == 2
            jsonResult.items[0].id == 2
            jsonResult.items[0].name == "Checkllist 2"
            jsonResult.items[0].done == Boolean.FALSE
            jsonResult.items[0].links.find{it.rel == "of-activity"}.href == "/users/personal-activities/2"

            jsonResult.items[1].id == 3
            jsonResult.items[1].name == "Checkllist 3"
            jsonResult.items[1].done == Boolean.TRUE
            jsonResult.items[1].links.find{it.rel == "of-activity"}.href == "/users/personal-activities/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value()
    }

    @Unroll
    def "Should return one checklist by ID"() {
        given:
            def uri = "/rs/users/personal-activities/activities-checklists/2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return an checklist and status 200"
            jsonResult.item.id == 2
            jsonResult.item.name == "Checkllist 2"
            jsonResult.item.done == Boolean.FALSE
            jsonResult.item.links.find{it.rel == "of-activity"}.href == "/users/personal-activities/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update only name of checklist"() {
        given:
            def uri = "/rs/users/personal-activities/activities-checklists/4"
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
            .content('''{"name": "teste update"}''')).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return an checklist and status 200"
            jsonResult.item.id == 4
            jsonResult.item.name == "teste update"
            jsonResult.item.done == Boolean.TRUE
            jsonResult.item.links.find{it.rel == "of-activity"}.href == "/users/personal-activities/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update only status of checklist"() {
        given:
            def uri = "/rs/users/personal-activities/activities-checklists/4"
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content('''{"done": "false"}''')).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return an checklist and status 200"
            jsonResult.item.id == 4
            jsonResult.item.done == Boolean.FALSE
            jsonResult.item.links.find{it.rel == "of-activity"}.href == "/users/personal-activities/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }


}
