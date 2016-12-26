package br.com.kproj.salesman.assistants.calendar.activities.specialization.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(ActivityNegotiationEndpoint)
class ActivityNegotiationEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES = "/assistants/calendar/activities.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES)
    }

    @Unroll
    def "Should create a calendar activity negotiation"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
            def uriContact = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-negotiations"
        when:
            def resultActivity = mockMvc.perform(post(uri)
                    .content(scenery("Should create a calendar activity with all data").json)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def activityCreated = new JsonSlurper().parseText(resultActivity.response.contentAsString)

            def resultActivityNegotiation = mockMvc.perform(post(uriContact.replace("{activityId}", activityCreated.item.id.toString()))
                .content('''{"negotiation": {"id":3}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def activityNegotiationCreated = new JsonSlurper().parseText(resultActivityNegotiation.response.contentAsString)

        then: "Should return a activity created"
            resultActivityNegotiation.response.status == HttpStatus.CREATED.value
            activityNegotiationCreated.item.id == activityCreated.item.id
            activityNegotiationCreated.item.links.find{it.rel == "of-negotiation"}.href == "/rs/customers/negotiations/3"
            activityNegotiationCreated.item.links.find{it.rel == "parent"}.href == "/rs/users/calendars/calendar-activities/${activityCreated.item.id}"
    }

    @Unroll
    def "Should not create a calendar activity negotiation with invalid negotiation"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
            def uriContact = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-negotiations"
        when:
            def resultActivity = mockMvc.perform(post(uri)
                    .content(scenery("Should create a calendar activity with all data").json)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def activityCreated = new JsonSlurper().parseText(resultActivity.response.contentAsString)

            def resultActivityNegotiation = mockMvc.perform(post(uriContact.replace("{activityId}", activityCreated.item.id.toString()))
                    .content('''{}''')
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return a bad request"
            resultActivityNegotiation.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity negotiation with invalid activity"() {
        given:
            def invalidActivityId = 9999
            def uriContact = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-negotiations"
        when:

            def resultActivityNegotiation = mockMvc.perform(post(uriContact.replace("{activityId}", invalidActivityId.toString()))
                .content('''{"negotiation": {"id":3}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return a bad request"
            resultActivityNegotiation.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity negotiation when already exists"() {
        given:
            def activityId = 3
            def uriContact = "/rs/users/calendars/calendar-activities/${activityId}/calendar-activities-negotiations"
        when:
            def resultActivityContact = mockMvc.perform(post(uriContact)
                .content('''{"negotiation": {"id":3}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return a bad request"
            resultActivityContact.response.status == HttpStatus.BAD_REQUEST.value
    }

}
