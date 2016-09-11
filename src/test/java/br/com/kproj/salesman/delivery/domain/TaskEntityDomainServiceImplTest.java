package br.com.kproj.salesman.delivery.domain;

import br.com.kproj.salesman.delivery.infrastructure.validators.TaskValidator;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.SalesOrderRepository;
import br.com.kproj.salesman.infrastructure.repository.UserEntityRepository;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TaskEntityDomainServiceImplTest {

    @InjectMocks
    private TaskDomainServiceImpl taskDomainService;

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @Mock
    private UserEntityRepository userEntityRepository;

    @Mock
    private TaskValidator validator;

    @Rule
    public ExpectedException throwing = ExpectedException.none();


    @Test
    public void shouldNotErrosWhenTaskIsOK () throws ParseException {
        TaskEntity taskEntityStub = getTaskStub();

        given(salesOrderRepository.exists(taskEntityStub.getSalesOrderEntity().getId())).willReturn(Boolean.TRUE);
        given(userEntityRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userEntityRepository.exists(3l)).willReturn(Boolean.TRUE);

        taskDomainService.checkBusinessRulesFor(taskEntityStub);
    }

    @Test
    public void shouldThrowExceptionWhenSalesOrderNotExist () throws ParseException {
        TaskEntity taskEntityStub = getTaskStub();
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskEntityStub.getSalesOrderEntity().getId())).willReturn(Boolean.FALSE);
        given(userEntityRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userEntityRepository.exists(3l)).willReturn(Boolean.TRUE);

        try {
            taskDomainService.checkBusinessRulesFor(taskEntityStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.sales.order.valid"), is(Boolean.TRUE));
    }

    @Test
    public void shouldThrowExceptionWhenExistAUserWithoutId () throws ParseException {
        TaskEntity taskEntityStub = getTaskStub();
        taskEntityStub.getSignedBy().get(0).setId(null);
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskEntityStub.getSalesOrderEntity().getId())).willReturn(Boolean.TRUE);
        given(userEntityRepository.exists(2l)).willReturn(Boolean.TRUE);
        given(userEntityRepository.exists(3l)).willReturn(Boolean.TRUE);


        try {
            taskDomainService.checkBusinessRulesFor(taskEntityStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.users.valid"), is(Boolean.TRUE));
    }

    @Test
    public void shouldThrowExceptionWhenExistAUserIsNull () throws ParseException {
        TaskEntity taskEntityStub = getTaskStub();
        taskEntityStub.getSignedBy().add(null);
        ValidationException exception = null;

        given(salesOrderRepository.exists(taskEntityStub.getSalesOrderEntity().getId())).willReturn(Boolean.TRUE);


        try {
            taskDomainService.checkBusinessRulesFor(taskEntityStub);
        } catch(ValidationException e) {
            exception = e;
        }

        assertThat(exception.getErrors().size(), is(1));
        assertThat(exception.getErrors().contains("task.verify.users.valid"), is(Boolean.TRUE));
    }

    private TaskEntity getTaskStub () throws ParseException {
        List<UserEntity> users = Lists.<UserEntity>newArrayList(new UserEntity(2l), new UserEntity(3l));

        TaskEntity taskEntityStub = new TaskEntity();
        SalesOrderEntity order = new SalesOrderEntity();
        order.setId(1l);
        taskEntityStub.setSalesOrderEntity(order);
        taskEntityStub.setSignedBy(users);
        taskEntityStub.setDeadline(DateUtils.parseDate("20/05/2030", "dd/MM/yyyy"));

        return taskEntityStub;
    }

}