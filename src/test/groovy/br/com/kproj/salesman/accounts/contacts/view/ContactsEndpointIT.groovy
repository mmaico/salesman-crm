package br.com.kproj.salesman.accounts.contacts.view

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put

@ClassReference(ContactsEndpoint)
class ContactsEndpointIT extends AbstractIntegrationTest {


    private static final String CONTACTS = "/accounts/contacts/contacts.json"


    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(CONTACTS)
    }

    @Unroll
    def "Should find all contacts by customer using paginated"() {
        given:
            def uri = "/rs/customers/1/contacts?page=0&size=1"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
            def jsonExpected = new JsonSlurper().parseText(scenery("Lista de contatos de um cliente paginado").json)
        then:
            jsonResult.uri == uri
            jsonResult.items.size == 1
            jsonResult.totalItems == 2
            jsonResult.pageSize == 1

            jsonResult.items[0] == jsonExpected.items[0]
    }

    @Unroll
    def "Should find one contact by ID"() {
        given:
            def uri = "/rs/customers/contacts/1"
        when:
            def mvcResult = mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn()
            def jsonExpected = new JsonSlurper().parseText(scenery("Contato buscando pelo ID").json)
            def jsonResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        then:
            jsonResult.item == jsonExpected.item
            jsonResult.uri == uri
    }

    @Unroll
    def "Should create a contact with all data"() {
        given:
            def uri = "/rs/customers/3/contacts"
            def newContact = new JsonSlurper().parseText(scenery("should create a contact with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newContact))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a new contact for customer 3"
            jsonResult.item.id != null
            jsonResult.item.name == newContact.name
            jsonResult.item.email == newContact.email
            jsonResult.item.phone == newContact.phone
            jsonResult.item.position == newContact.position

            jsonResult.item.links.size == 1
            jsonResult.item.links.find{it.rel == "of-customer"}.href == "/customers/3"

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.CREATED.value
    }

    @Unroll
    def "Should not create a contact with without name"() {
        given:
            def uri = "/rs/customers/3/contacts"
            def newContact = new JsonSlurper().parseText(scenery("should not create a contact without name").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newContact))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a 400"
            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should not create a contact with invalid email"() {
        given:
            def uri = "/rs/customers/3/contacts"
            def newContact = new JsonSlurper().parseText(scenery("should not create a contact with invalid email").getJson())
        when:
            def mvcResult = mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(newContact))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a 400"
            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should update a contact with all data"() {
        given:
            def uri = "/rs/customers/contacts/4"
            def contactWithAllData = new JsonSlurper().parseText(scenery("should update a contact with all data").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(contactWithAllData))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a contact with updated data"
                jsonResult.item.id == 4
                jsonResult.item.name == contactWithAllData.name
                jsonResult.item.email == contactWithAllData.email
                jsonResult.item.phone == contactWithAllData.phone
                jsonResult.item.position == contactWithAllData.position

                jsonResult.uri == uri
                mvcResult.response.status == HttpStatus.OK.value
    }

    @Unroll
    def "Should not update a contact with invalid email"() {
        given:
            def uri = "/rs/customers/contacts/4"
            def contactWithAllData = new JsonSlurper().parseText(scenery("should not update a contact with invalid email").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(toJson(contactWithAllData))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a bad request"
            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.BAD_REQUEST.value
    }

    @Unroll
    def "Should update contact with not required fields"() {
        given:
            def uri = "/rs/customers/contacts/4"
            def contactWithFieldsNotRequired = new JsonSlurper().parseText(scenery("should update contact with not required fields").getJson())
        when:
            def mvcResult = mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(contactWithFieldsNotRequired))).andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Should return a contact with updated data"
            jsonResult.item.id == 4
            jsonResult.item.name == "Jose luiz"
            jsonResult.item.email == contactWithFieldsNotRequired.email
            jsonResult.item.phone == contactWithFieldsNotRequired.phone
            jsonResult.item.position == contactWithFieldsNotRequired.position

            jsonResult.uri == uri
            mvcResult.response.status == HttpStatus.OK.value
    }
}
