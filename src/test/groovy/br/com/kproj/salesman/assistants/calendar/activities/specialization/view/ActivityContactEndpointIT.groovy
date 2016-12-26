package br.com.kproj.salesman.assistants.calendar.activities.specialization.view

import br.com.kproj.salesman.assistants.calendar.activities.activity.view.ActivityEndpoint
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

@ClassReference(ActivityContactEndpoint)
class ActivityContactEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES = "/assistants/calendar/activities.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES)
    }

    @Unroll
    def "Should create a calendar activity contact"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
            def uriContact = "/rs/users/calendars/calendar-activities/{activityId}/calendar-activities-contacts"
        when:
            def resultActivity = mockMvc.perform(post(uri)
                    .content(scenery("Should create a calendar activity with all data").json)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def activityCreated = new JsonSlurper().parseText(resultActivity.response.contentAsString)

            def resultActivityContact = mockMvc.perform(post(uriContact.replace("{activityId}", activityCreated.item.id.toString()))
                .content('''{"contact": {"id":3}}''')
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def activityContactCreated = new JsonSlurper().parseText(resultActivityContact.response.contentAsString)

        then: "Should return a activity created"
            resultActivityContact.response.status == HttpStatus.CREATED.value
            activityContactCreated.item.id == activityCreated.item.id
            activityContactCreated.item.links.find{it.rel == "of-contact"}.href == "/contacts/3"
            activityContactCreated.item.links.find{it.rel == "parent"}.href == "/rs/users/calendars/calendar-activities/${activityCreated.item.id}"
    }
    
}
