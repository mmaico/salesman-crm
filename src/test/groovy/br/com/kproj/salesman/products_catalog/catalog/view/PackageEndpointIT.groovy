package br.com.kproj.salesman.products_catalog.catalog.view

import br.com.kproj.salesman.infratest.AbstractIntegrationTest
import br.com.kproj.salesman.infratest.ClassReference
import br.com.kproj.salesman.infratest.SceneryLoaderHelper
import br.com.kproj.salesman.products_catalog.catalog.domain.model.saleables.SaleableUnitRepository
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Stepwise
import spock.lang.Unroll

import static br.com.kproj.salesman.infratest.JsonCompareUtil.isEquals
import static br.com.kproj.salesman.infratest.SceneryLoaderHelper.scenery
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Stepwise
@ClassReference(SaleableEndpoint)
class PackageEndpointIT extends AbstractIntegrationTest {

    private static final String PACKAGES_CREATED = "/products_catalog/packages-create.json";
    private static final String PACKAGES_OPERATIONS = "/products_catalog/packages-operations.json";

    def MockMvc mockMvc

    @Autowired
    def WebApplicationContext webApplicationContext

    @Autowired
    def SaleableUnitRepository repository

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build()
        SceneryLoaderHelper.load(PACKAGES_CREATED)
        SceneryLoaderHelper.load(PACKAGES_OPERATIONS)
    }

    @Unroll
    def "Creating a package"() {

        def mvcResult = mockMvc.perform(post("/rs/saleables")
                .content(scenery("Criacao de saleable para pacote").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def jsonResult = mvcResult.getResponse().getContentAsString()
        def saleable = new JsonSlurper().parseText(jsonResult)

        def uri = "/rs/saleables/${saleable.item.id}/packages"

        def packageMvc = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def packageResult = new JsonSlurper().parseText(packageMvc.getResponse().getContentAsString())
        def status = packageMvc.getResponse().getStatus()

        def packageExpected = new JsonSlurper().parseText(scenery("Json de pacote criado esperado").getJson())

        expect: "a salepackage resource with http status 201 "
            status == HttpStatus.CREATED.value()
            packageResult.item.id == saleable.item.id
            packageResult.item.saleable.id == saleable.item.id
            packageResult.item.saleable.name == packageExpected.item.saleable.name
            packageResult.item.saleable.description == packageExpected.item.saleable.description
            packageResult.item.saleable.active == packageExpected.item.saleable.active
            packageResult.item.saleable.price == packageExpected.item.saleable.price
            packageResult.item.saleable.priceCost == packageExpected.item.saleable.priceCost
            packageResult.uri == uri
    }

    @Unroll
    def "Adding a saleable(service/product) in the package"() {
        def resultExpected = scenery("Resultado experado ao executar a operacao de adicionar um produto no pacote").getJson()

        def mvcResult = mockMvc.perform(patch("/rs/saleables/packages/4")
                .content(scenery("Operacao para adicionar um produto no pacote").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def packageResult = mvcResult.getResponse().getContentAsString()
        def statusResult = mvcResult.getResponse().getStatus()

        expect: "a salepackage with 2 saleables and http status 200"
            isEquals(packageResult, resultExpected)
            statusResult == HttpStatus.OK.value()
    }

    @Unroll
    def "Remove a saleable(service/product) of the package"() {

        def mvcResult = mockMvc.perform(patch("/rs/saleables/packages/7")
                .content(scenery("Operacao para remover um saleable de um pacote").getJson())
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        def packageResult = new JsonSlurper().parseText(mvcResult.getResponse().getContentAsString())
        def statusResult = mvcResult.getResponse().getStatus()

        expect: "a salepackage wihtout saleables and http status 200"
            packageResult.item.saleables.size() == 1
            packageResult.item.saleables[0].id == 1
            statusResult == HttpStatus.OK.value()
    }


}
