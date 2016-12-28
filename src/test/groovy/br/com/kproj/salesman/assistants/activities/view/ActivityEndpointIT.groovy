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

import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ClassReference(ActivityEndpoint)
class ActivityEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES_CREATE = "/assistants/activities/activity-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES_CREATE)
    }

    @Unroll
    def "Should create a activity with all data"() {
        given:
            def uri = "/rs/users/2/personal-activities"
            def newActivity = new JsonSlurper().parseText(scenery("Should create a activity with all data").getJson())
        when:

            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newActivity))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new activity"
            jsonResult.item.id != null
            jsonResult.item.title == newActivity.title
            jsonResult.item.description == newActivity.description
            jsonResult.item.deadline != null
            jsonResult.item.status == "WAITING"

            jsonResult.item.links.size == 2
            jsonResult.item.links.find{it.rel == "of-owner"}.href == "/users/2"
            jsonResult.item.links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/${jsonResult.item.id}/activities-checklists"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a activity without title"() {
        given:
            def uri = "/rs/users/2/personal-activities"
            def newActivity = new JsonSlurper().parseText(scenery("Should not create a activity without title").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newActivity))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a activity with deadline less than current date"() {
        given:
            def uri = "/rs/users/2/personal-activities"
            def newActivity = new JsonSlurper()
                    .parseText(scenery("Should not create a activity with deadline less than current date").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newActivity))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a activity with invalid owner"() {
        given:
            def uri = "/rs/users/9999/personal-activities"
            def newTask = new JsonSlurper().parseText(scenery("Should create a activity with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newTask))).andReturn()

        then: "Should return a bad request"
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should update a activity with all data"() {
        given:
            def uri = "/rs/users/personal-activities/4"
            def updateActivity = new JsonSlurper().parseText(scenery("Should update a activity with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updateActivity))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a activity updated"
            jsonResult.item.id == 4
            jsonResult.item.title == updateActivity.title
            jsonResult.item.description == updateActivity.description
            jsonResult.item.deadline == "2019-09-05T00:00:00.000+0000"
            jsonResult.item.status == "IN_PROGRESS"

            jsonResult.item.links.size == 3
            jsonResult.item.links.find{it.rel == "of-owner"}.href == "/users/2"
            jsonResult.item.links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/${jsonResult.item.id}/activities-checklists"
            jsonResult.item.links.find{it.rel == "is-a-subactivity"}.href == "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/4"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update only title of activity"() {
        given:
            def uri = "/rs/users/personal-activities/4"
            def updateActivity = new JsonSlurper().parseText(scenery("Should update only title of activity").getJson())
        when:
        def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updateActivity))).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a activity updated"
        jsonResult.item.id == 4
        jsonResult.item.title == updateActivity.title

        jsonResult.item.links.size == 3
        jsonResult.item.links.find{it.rel == "of-owner"}.href == "/users/2"
        jsonResult.item.links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/${jsonResult.item.id}/activities-checklists"
        jsonResult.item.links.find{it.rel == "is-a-subactivity"}.href == "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/4"

        jsonResult.uri == uri
        mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should update only status of activity"() {
        given:
            def uri = "/rs/users/personal-activities/4"
            def updateActivity = new JsonSlurper().parseText(scenery("Should update only status of activity").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updateActivity))).andReturn()

        def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a activity updated"
            jsonResult.item.id == 4
            jsonResult.item.status == "DONE"

            jsonResult.item.links.size == 3
            jsonResult.item.links.find{it.rel == "of-owner"}.href == "/users/2"
            jsonResult.item.links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/${jsonResult.item.id}/activities-checklists"
            jsonResult.item.links.find{it.rel == "is-a-subactivity"}.href == "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/4"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find activity by owner"() {
        given:
            def uri = "/rs/users/1/personal-activities"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return activities"
            jsonResult.items.size == 2

            jsonResult.items[0].id == 1
            jsonResult.items[0].title == "Title One"
            jsonResult.items[0].description == "descritpion 1"
            jsonResult.items[0].deadline == "2019-02-10T02:00:00.000+0000"
            jsonResult.items[0].status == "WAITING"

            jsonResult.items[1].id == 3
            jsonResult.items[1].title == "Title 3"
            jsonResult.items[1].description == "descritpion 3"
            jsonResult.items[1].deadline == "2019-02-10T02:00:00.000+0000"
            jsonResult.items[1].status == "IN_PROGRESS"

            jsonResult.items[0].links.size == 3
            jsonResult.items[0].links.find{it.rel == "of-owner"}.href == "/users/1"
            jsonResult.items[0].links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/1/activities-checklists"
            jsonResult.items[0].links.find{it.rel == "is-a-rootactivity"}.href == "/rs/users/personal-activities/personal-root-activities/1"

            jsonResult.items[1].links.size == 3
            jsonResult.items[1].links.find{it.rel == "of-owner"}.href == "/users/1"
            jsonResult.items[1].links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/3/activities-checklists"
            jsonResult.items[1].links.find{it.rel == "is-a-rootactivity"}.href == "/rs/users/personal-activities/personal-root-activities/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find a activity by ID"() {
        given:
            def uri = "/rs/users/personal-activities/2"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return activity"

            jsonResult.item.id == 2
            jsonResult.item.title == "Title 2"
            jsonResult.item.description == "descritpion 2"
            jsonResult.item.deadline == "2019-02-10T02:00:00.000+0000"
            jsonResult.item.status == "IN_PROGRESS"

            jsonResult.item.links.size == 3
            jsonResult.item.links.find{it.rel == "of-owner"}.href == "/users/2"
            jsonResult.item.links.find{it.rel == "has-checklists"}.href == "/rs/users/personal-activities/2/activities-checklists"
            jsonResult.item.links.find{it.rel == "is-a-rootactivity"}.href == "/rs/users/personal-activities/personal-root-activities/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

}
