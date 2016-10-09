package br.com.kproj.salesman.infratest

import br.com.kproj.salesman.Application
import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener
import org.springframework.test.context.support.DirtiesContextTestExecutionListener
import org.springframework.test.context.transaction.TransactionalTestExecutionListener
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest(classes = Application, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestExecutionListeners([DependencyInjectionTestExecutionListener,
        DirtiesContextTestExecutionListener,
        TransactionalTestExecutionListener,
        DbUnitTestExecutionListener, ForeignKeyDisablingListener])
@DatabaseSetup("/dbunit-dataset.xml")
@ActiveProfiles("test")
@TestPropertySource("/application.yml")
@Transactional
class AbstractIntegrationTest extends Specification {

}
