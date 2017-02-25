package br.com.kproj.salesman.medias.media.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.ImageStore
import groovy.json.JsonSlurper
import org.apache.camel.RoutesBuilder
import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ClassReference(MediaEndpoint)
class MediaEndpointIT extends AbstractIntegrationTest {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    "Should find one media by ID"() {
        given:
            def uri = "/rs/storages/medias/3"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item.id == 3
            jsonResult.item.name == "839938"
            jsonResult.item.mimeType == "application/pdf"
            jsonResult.item.size == 432
            jsonResult.item.url == "http://fsjjkh-file/fds"
            jsonResult.item.links.find{it.rel == "of-storage"}.href == "/rs/storages/3"
            jsonResult.uri == uri

            mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    "Should return 404 when media not found"() {
        given:
            def uri = "/rs/storages/medias/6666"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
        then:
            mvcResult.response.status == HttpStatus.NOT_FOUND.value
    }

    @Unroll
    "Should create a media"() {
        given:
            def uri = """/rs/storages/name=timeline-company/medias"""
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content("""{"image": "$ImageStore.IMAGE_64"}""")).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new media"
            jsonResult.item.id != null
            jsonResult.item.name != null
            jsonResult.item.mimeType == "image/jpeg"
            jsonResult.item.size == 12943
            jsonResult.item.url == "http://s3/file88498"
            jsonResult.item.links.find{it.rel == "of-storage"}.href == "/rs/storages/3"
            jsonResult.uri == uri

            mvcResult.response.status == HttpStatus.CREATED.value
    }


    @Configuration
    static class Application {

        @Bean
        RoutesBuilder routeBuilder() {
            return new RouteBuilder() {

                @Override
                void configure() throws Exception {
                    from("direct:store-timeline-company")
                            .transform(constant("http://s3/file88498"))
                }
            }
        }
    }

}
