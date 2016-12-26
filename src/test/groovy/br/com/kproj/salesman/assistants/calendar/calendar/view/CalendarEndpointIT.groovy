package br.com.kproj.salesman.assistants.calendar.calendar.view

import br.com.kproj.salesman.assistants.calendar.calendar.view.CalendarEndpoint
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(CalendarEndpoint)
class CalendarEndpointIT extends AbstractIntegrationTest {

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    def "Should find calendar by ID"() {
        given:
            def uri = "/rs/users/calendars/2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return 1 calendar"
            mvcResult.response.status == HttpStatus.OK.value

            resultJson.item.id == 2
            resultJson.item.links.size == 2

            resultJson.item.links.find{it.rel == "owner"}.href == "/users/1"
            resultJson.item.links.find{it.rel == "has-activities"}.href == "/rs/users/calendars/2/calendar-activities"
    }

    @Unroll
    def "Should create a calendar for user"() {
        given:
            def uri = "/rs/users/calendars"
        when:
            def mvcResult = mockMvc.perform(post(uri).content('''{"owner": {"id": 4}}''')
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return 1 calendar"
            mvcResult.response.status == HttpStatus.CREATED.value
            resultJson.item.id != null
            resultJson.item.links.size == 2

            resultJson.item.links.find{it.rel == "owner"}.href == "/users/4"
            resultJson.item.links.find{it.rel == "has-activities"}.href == "/rs/users/calendars/${resultJson.item.id}/calendar-activities"
    }

    @Unroll
    def "Should not create a calendar when already exists calendar for user"() {
        given:
            def uri = "/rs/users/calendars"
        when:
            def mvcResult = mockMvc.perform(post(uri).content('''{"owner": {"id": 3}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return 1 calendar"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar when user not informed"() {
        given:
            def uri = "/rs/users/calendars"
        when:
            def mvcResult = mockMvc.perform(post(uri).content('''{"owner": {}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return 1 calendar"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

}
