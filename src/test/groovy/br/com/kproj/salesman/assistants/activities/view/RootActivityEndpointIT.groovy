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

@ClassReference(RootActivityEndpoint)
class RootActivityEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES_CREATE = "/assistants/activities/activity-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES_CREATE)
    }

    @Unroll
    def "Should create a rootactivity "() {
        given:
            def uri = "/rs/users/1/personal-activities"
            def uriRootactivity = "/rs/users/personal-activities/{activityId}/personal-root-activities"
            def newActivity = new JsonSlurper().parseText(scenery("Should create a activity with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newActivity))).andReturn()

            def activity = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

            def rootActivityMvc = mockMvc.perform(post(uriRootactivity.replace("{activityId}", activity.item.id.toString()))
                    .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(newActivity))).andReturn()

            def rootactivity = new JsonSlurper().parseText(rootActivityMvc.response.getContentAsString())

        then: "Should return a new root activity"
            rootactivity.item.id == activity.item.id
            rootactivity.item.activity.id == activity.item.id
            rootactivity.item.activity.title == "Activity 0"
            rootactivity.item.activity.description == "Activity One description 0"
            rootactivity.item.activity.deadline == "2018-09-05T00:00:00.000+0000"
            rootactivity.item.activity.links[0].rel == "of-owner"
            rootactivity.item.activity.links[0].href == "/users/1"

            rootactivity.item.links[0].rel == "children"
            rootactivity.item.links[0].href == "/rs/users/personal-activities/personal-root-activities/${activity.item.id}/personal-sub-activities"

            rootactivity.uri == "/rs/users/personal-activities/${activity.item.id}/personal-root-activities"
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should find a rootactivity by ID"() {
        given:
            def uriRootactivity = "/rs/users/personal-activities/personal-root-activities/2"
        when:
            def rootActivityMvc = mockMvc.perform(get(uriRootactivity)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def rootactivity = new JsonSlurper().parseText(rootActivityMvc.response.getContentAsString())

        then: "Should return a root activity"
            rootactivity.item.id == 2
            rootactivity.item.activity.id == 2
            rootactivity.item.activity.title == "Title 2"
            rootactivity.item.activity.description == "descritpion 2"
            rootactivity.item.activity.deadline == "2019-02-10T02:00:00.000+0000"
            rootactivity.item.activity.links[0].rel == "of-owner"
            rootactivity.item.activity.links[0].href == "/users/2"

            rootactivity.item.links[0].rel == "children"
            rootactivity.item.links[0].href == "/rs/users/personal-activities/personal-root-activities/2/personal-sub-activities"

            rootactivity.uri == uriRootactivity
            rootActivityMvc.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find all rootactivity by owner"() {
        given:
            def uriRootactivity = "/rs/users/1/personal-activities/personal-root-activities"
        when:
            def rootActivityMvc = mockMvc.perform(get(uriRootactivity)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def rootactivity = new JsonSlurper().parseText(rootActivityMvc.response.getContentAsString())

        then: "Should return all root activity"
            rootactivity.items.size == 2
            rootActivityMvc.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should return error when activity have specialization when try rootActivity "() {
        given:
            def uriRootactivity = "/rs/users/personal-activities/4/personal-root-activities"
        when:
            def rootActivityMvc = mockMvc.perform(post(uriRootactivity)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Should return a bad request"
            rootActivityMvc.response.status == HttpStatus.BAD_REQUEST.value
    }

}
