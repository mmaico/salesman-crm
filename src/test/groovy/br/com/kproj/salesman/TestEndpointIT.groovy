package br.com.kproj.salesman

import br.com.kproj.salesman.accounts.addresses.view.AddressesEndpoint
import br.com.kproj.salesman.assistants.calendar.domain.model.activity.Activity
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.ActivityRepositoryHibernate
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarContactActivityRepository
import br.com.kproj.salesman.assistants.calendar.infrastructure.persistence.springdata.CalendarEntityRepositorySpringdata
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.CalendarActivityEntity
import br.com.kproj.salesman.infrastructure.entity.assistants.calendar.activity_specialization.CalendarActivityContactEntity
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

@ClassReference(AddressesEndpoint)
class TestEndpointIT extends AbstractIntegrationTest {

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def CalendarEntityRepositorySpringdata repository

    @Autowired
    def CalendarContactActivityRepository contactActivityRepository


    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
    }

    @Unroll
    def "Should find all addresses by customer using paginated"() {
        CalendarActivityEntity activity = new CalendarActivityEntity()
        activity.setDescription("teste")
        activity.setTitle("title")

        def activitySaved = repository.save(activity)

        CalendarActivityContactEntity contactActivity = new CalendarActivityContactEntity()
        contactActivity.setId(activitySaved.getId())

        def save = contactActivityRepository.save(contactActivity)
        System.out.println("testestetetsetsteteststset")

        expect:
            System.out.println("testestetetsetsteteststset")
    }


}
