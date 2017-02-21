package br.com.kproj.salesman.administration.users.view

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

@ClassReference(UserEndpoint)
class UserEndpointIT extends AbstractIntegrationTest {

    MockMvc mockMvc

    @Autowired
    WebApplicationContext webApplicationContext

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    "Should return all users"() {
        given:
            def uri = "/rs/users"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return all users"
            jsonResult.items.size == 4

            jsonResult.items[0].id == 1
            jsonResult.items[0].name == "Maico"
            jsonResult.items[0].login == "mmaico"
            jsonResult.items[0].password == "1234"
            jsonResult.items[0].lastname == "marcelo"
            jsonResult.items[0].email == "marcelo@gmail.com"
            jsonResult.items[0].links.find{it.rel == "position"}.href == "/rs/positions/1"
            jsonResult.items[0].links.find{it.rel == "branch"}.href == "/rs/branchs/2"

            jsonResult.items[1].id == 2
            jsonResult.items[1].name == "Maico1"
            jsonResult.items[1].login == "mmaico1"
            jsonResult.items[1].password == "1234"
            jsonResult.items[1].lastname == "marcelo1"
            jsonResult.items[1].email == "marcelo1@gmail.com"
            jsonResult.items[1].links.find{it.rel == "position"}.href == "/rs/positions/2"
            jsonResult.items[1].links.find{it.rel == "branch"}.href == "/rs/branchs/2"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value()
    }


    @Unroll
    "Should find by ID"() {
        given:
        def uri = "/rs/users/4"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should a user with id 4"

            jsonResult.item.id == 4
            jsonResult.item.name == "Maico3"
            jsonResult.item.login == "mmaico3"
            jsonResult.item.password == "1234"
            jsonResult.item.lastname == "marcelo3"
            jsonResult.item.email == "marcelo3@gmail.com"
            jsonResult.item.links.find{it.rel == "position"}.href == "/rs/positions/2"
            jsonResult.item.links.find{it.rel == "branch"}.href == "/rs/branchs/1"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value()
    }

}
