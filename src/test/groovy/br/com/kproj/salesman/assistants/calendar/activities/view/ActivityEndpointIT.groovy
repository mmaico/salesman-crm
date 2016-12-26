package br.com.kproj.salesman.assistants.calendar.activities.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@ClassReference(ActivityEndpoint)
class ActivityEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES = "/assistants/calendar/activities.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES)
    }

    @Unroll
    def "Should find activities calendar"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return all activities"
            mvcResult.response.status == HttpStatus.OK.value
            resultJson.items.size == 10
            resultJson.totalItems == 10
            resultJson.pageSize == 400
            resultJson.items[0].id == 1
            resultJson.items[0].title == "Title 1"
            resultJson.items[0].description == "Description 1"
            resultJson.items[0].location == "location 1"
            resultJson.items[0].start == "2015-02-10T00:00:00.000-0200"
            resultJson.items[0].end == "2015-02-15T00:00:00.000-0200"
            resultJson.items[0].allDay == true
            resultJson.items[0].links.size == 2

            resultJson.items[0].links.find{it.rel == "calendar-activities-contact"}.href == "/rs/users/calendars/calendar-activities/calendar-activities-contacts/1"
            resultJson.items[0].links.find{it.rel == "of-calendar"}.href == "/users/calendars/1"
    }

    @Unroll
    def "Should find activities calendar by range date" () {
        given:
            def start = "2015-02-16T00:00:00Z"
              def end = "2015-02-21T23:59:59Z"
            def uri = "/rs/users/calendars/1/calendar-activities?filter=start.ge($start).and(start.le($end))"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return activities by filter"
            mvcResult.response.status == HttpStatus.OK.value
            resultJson.items.size == 5
            resultJson.totalItems == 5
            resultJson.pageSize == 400
            resultJson.items[0].id == 3
            resultJson.items[1].id == 4
            resultJson.items[2].id == 5
            resultJson.items[3].id == 6
            resultJson.items[4].id == 7
    }

    @Unroll
    def "Should create a calendar activity with all data"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                    .content(scenery("Should create a calendar activity with all data").json)
                    .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return a activity created"
            mvcResult.response.status == HttpStatus.CREATED.value
            resultJson.item.id != null
            resultJson.item.title == "Reuniao com o Jose"
            resultJson.item.description == "Reuniao para alinhamento do projeto"
            resultJson.item.location == "Faria Lima, 778"
            resultJson.item.start == "2015-02-10T14:00:00.000-0200"
            resultJson.item.end == "2015-02-10T15:00:00.000-0200"
            resultJson.item.allDay == false
            resultJson.item.links.size == 1
            resultJson.item.links[0].rel == "of-calendar"
            resultJson.item.links[0].href == "/users/calendars/1"
    }

    @Unroll
    def "Should create a calendar activity with only required data"() {
        given:
        def uri = "/rs/users/calendars/1/calendar-activities"
        when:
        def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should create a calendar activity with only required data").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return a activity created"
        mvcResult.response.status == HttpStatus.CREATED.value
        resultJson.item.id != null
        resultJson.item.title == "Reuniao com o Jose"
        resultJson.item.description == null
        resultJson.item.location == null
        resultJson.item.start == "2015-02-10T14:00:00.000-0200"
        resultJson.item.end == "2015-02-10T15:00:00.000-0200"
        resultJson.item.allDay == false
        resultJson.item.links.size == 1
        resultJson.item.links[0].rel == "of-calendar"
        resultJson.item.links[0].href == "/users/calendars/1"
    }

    @Unroll
    def "Should not create a calendar activity without title"() {
        given:
             def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should not create a calendar activity without title").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity without start date"() {
        given:
             def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should not create a calendar activity without start date").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity without end date"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should not create a calendar activity without end date").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity with AllDay true  and start e end with hours"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should not create a calendar activity with AllDay true  and start e end with hours").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a calendar activity with start date greater than end date"() {
        given:
            def uri = "/rs/users/calendars/1/calendar-activities"
        when:
            def mvcResult = mockMvc.perform(post(uri)
                .content(scenery("Should not create a calendar activity with start date greater than end date").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should update all data of calendar acitivity"() {
        given:
            def uri = "/rs/users/calendars/calendar-activities/10"
        when:
            def mvcResult = mockMvc.perform(put(uri)
                .content(scenery("Should update all data of calendar activity").json)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()
            def resultJson = new JsonSlurper().parseText(mvcResult.response.contentAsString)
        then: "Should return a activity updated"
            mvcResult.response.status == HttpStatus.OK.value
            resultJson.item.id == 10
            resultJson.item.title == "Reuniao com o Jose update"
            resultJson.item.description == "Reuniao para alinhamento do projeto update"
            resultJson.item.location == "Faria Lima, 778 update"
            resultJson.item.start == "2016-03-11T18:00:00.000-0200"
            resultJson.item.end == "2016-03-11T19:00:00.000-0200"
            resultJson.item.allDay == false
            resultJson.item.links.size == 2
            resultJson.item.links.find{it.rel == "of-calendar" }.href == "/users/calendars/1"
            resultJson.item.links.find{it.rel == "calendar-activities-negotiation"}.href == "/rs/users/calendars/calendar-activities/calendar-activities-negotiations/10"
    }


}
