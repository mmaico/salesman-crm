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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@ClassReference(SubActivityEndpoint)
class SubActivityEndpointIT extends AbstractIntegrationTest {

    private static final String ACTIVITIES_CREATE = "/assistants/activities/activity-create.json"

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(ACTIVITIES_CREATE)
    }

    @Unroll
    def "Should create a subActivity "() {
        given:
            def uri = "/rs/users/1/personal-activities"
            def uriSubactivity = "/rs/users/personal-activities/personal-root-activities/2/personal-sub-activities"
            def newActivity = new JsonSlurper().parseText(scenery("Should create a activity with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newActivity))).andReturn()

            def activity = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

            def subActivityMvc = mockMvc.perform(post(uriSubactivity).content("{\"activity\": {\"id\":$activity.item.id}}").contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subactivity = new JsonSlurper().parseText(subActivityMvc.response.getContentAsString())

        then: "Should return a new sub activity"
            subactivity.item.id == activity.item.id
            subactivity.item.activity.id == activity.item.id
            subactivity.item.activity.title == newActivity.title
            subactivity.item.activity.description == newActivity.description
            subactivity.item.activity.deadline == "2018-09-05T00:00:00.000+0000"
            subactivity.item.activity.links[0].rel == "of-owner"
            subactivity.item.activity.links[0].href == "/users/1"

            subactivity.item.links[0].rel == "child-of"
            subactivity.item.links[0].href == "/users/personal-activities/personal-root-activities/2"

            subactivity.uri == uriSubactivity
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should find a subactivity by ID"() {
        given:
            def uriSubactivity = "/rs/users/personal-activities/personal-root-activities/personal-sub-activities/5"
        when:
            def mvcResult = mockMvc.perform(get(uriSubactivity)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subActvity = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a sub activity"
            subActvity.item.id == 5
            subActvity.item.activity.id == 5
            subActvity.item.activity.title == "Title 5"
            subActvity.item.activity.description == "descritpion 5"
            subActvity.item.activity.deadline == "2019-02-10T02:00:00.000+0000"
            subActvity.item.activity.links[0].rel == "of-owner"
            subActvity.item.activity.links[0].href == "/users/2"

            subActvity.item.links[0].rel == "child-of"
            subActvity.item.links[0].href == "/users/personal-activities/personal-root-activities/1"

            subActvity.uri == uriSubactivity
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should find all subactivity by rootactivity"() {
        given:
            def uriSubactivities = "/rs/users/personal-activities/personal-root-activities/1/personal-sub-activities"
        when:
            def subactivityMvc = mockMvc.perform(get(uriSubactivities).contentType(MediaType.APPLICATION_JSON)).andReturn()

            def subactivity = new JsonSlurper().parseText(subactivityMvc.response.getContentAsString())

        then: "Should return all sub activity"
            subactivity.items.size == 2
            subactivityMvc.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should return error when activity have specialization when try rootActivity"() {
        given:
            def uriSubactivity = "/rs/users/personal-activities/personal-root-activities/2/personal-sub-activities"
        when:
            def subActivityMvc = mockMvc.perform(post(uriSubactivity).content("{\"activity\": {\"id\":4}}").contentType(MediaType.APPLICATION_JSON)).andReturn()
        then: "Should return a bad request"
            subActivityMvc.response.status == HttpStatus.BAD_REQUEST.value
    }

}
