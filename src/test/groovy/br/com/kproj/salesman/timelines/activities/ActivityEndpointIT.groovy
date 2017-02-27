package br.com.kproj.salesman.timelines.activities

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.timelines.activities.view.ActivityEndpoint
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@ClassReference(ActivityEndpoint)
class ActivityEndpointIT extends AbstractIntegrationTest {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    "should find all activities by timeline"() {
        given:
            def uri = "/rs/timelines/2/activities"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.items.size == 2

            jsonResult.items[0].id == 1
            jsonResult.items[0].description == "description one"
            jsonResult.items[0].creation == "2016-02-10T02:00:00.000+0000"
            jsonResult.items[0].tag == "NOTE"
            jsonResult.items[0].links.find{it.rel == "of-user"}.href == "/rs/users/1"
            jsonResult.items[0].links.find{it.rel == "of-timeline"}.href == "/rs/timelines/2"
            jsonResult.items[0].links.find{it.rel == "has-medias"}.href == "/rs/timelines/activities/1/activities-medias-relationships"

            jsonResult.items[1].id == 3
            jsonResult.items[1].description == "description 3"
            jsonResult.items[1].creation == "2016-05-10T03:00:00.000+0000"
            jsonResult.items[1].tag == "EMAIL"
            jsonResult.items[1].links.find{it.rel == "of-user"}.href == "/rs/users/1"
            jsonResult.items[1].links.find{it.rel == "of-timeline"}.href == "/rs/timelines/2"
            jsonResult.items[1].links.find{it.rel == "has-medias"}.href == "/rs/timelines/activities/3/activities-medias-relationships"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "should find by ID"() {
        given:
            def uri = "/rs/timelines/activities/3"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item.id == 3
            jsonResult.item.description == "description 3"
            jsonResult.item.creation == "2016-05-10T03:00:00.000+0000"
            jsonResult.item.tag == "EMAIL"
            jsonResult.item.links.find{it.rel == "of-user"}.href == "/rs/users/1"
            jsonResult.item.links.find{it.rel == "of-timeline"}.href == "/rs/timelines/2"
            jsonResult.item.links.find{it.rel == "has-medias"}.href == "/rs/timelines/activities/3/activities-medias-relationships"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }

//    @Unroll
//    "should return 404 when not exists"() {
//        given:
//            def uri = "/rs/timelines/activities/activities-medias-relationships/6666"
//        when:
//            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
//        then:
//            mvcResult.response.status == HttpStatus.NOT_FOUND.value
//    }
//
//    @Unroll
//    "Should create a new relation between activity and media"() {
//        given:
//            def uri = "/rs/timelines/activities/2/activities-medias-relationships"
//        when:
//            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
//                    .content("""{"media":{"id": 6}}""")).andReturn()
//
//            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())
//
//        then: "Should return a new media"
//            jsonResult.item.id != null
//            jsonResult.item.links.find{it.rel == "activity"}.href == "/rs/timelines/activities/2"
//            jsonResult.item.links.find{it.rel == "media"}.href == "/rs/storages/medias/6"
//            jsonResult.uri == uri
//
//            mvcResult.response.status == HttpStatus.CREATED.value
//    }
//
//    @Unroll
//    "Should delete a relation between activity and media"() {
//        given:
//            def uri = "/rs/timelines/activities/activities-medias-relationships/4"
//        when:
//            def mvcResult = mockMvc.perform(delete(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
//        then: "Should return a new media"
//            mvcResult.response.status == HttpStatus.OK.value
//    }
//

}
