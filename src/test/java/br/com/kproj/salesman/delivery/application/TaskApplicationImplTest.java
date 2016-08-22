package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.application.tasks.TaskApplicationImpl;
import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import br.com.kproj.salesman.infrastructure.entity.OperationRegion;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrder;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.entity.task.TaskChangeHistory;
import br.com.kproj.salesman.infrastructure.events.messages.TaskChangeStatusMessage;
import br.com.kproj.salesman.infrastructure.exceptions.ValidationException;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskApplicationImplTest {

    @InjectMocks
    private TaskApplicationImpl service;

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskDomainService domainService;

    @Mock
    private SalesOrderTaskItemProcessor processor;

    @Mock
    private TaskChangeHistoryRepository changeHistoryRepository;

    @Mock
    private EventBus eventBus;

    @Captor
    private ArgumentCaptor<TaskChangeStatusMessage> messageCaptor;


    @Test
    public void shouldRegisterANewTask() {
        Task taskMock = Mockito.mock(Task.class);

        given(taskMock.isNew()).willReturn(Boolean.TRUE);
        given(repository.save(taskMock)).willReturn(taskMock);
        service.register(taskMock);

        verify(domainService).checkBusinessRulesFor(taskMock);
        verify(domainService).prepareToSave(taskMock);
    }

    @Test
    public void shouldUpdateATask() {
        Task taskMock = Mockito.mock(Task.class);

        given(taskMock.getId()).willReturn(1l);
        given(taskMock.isNew()).willReturn(Boolean.FALSE);
        given(repository.findOne(1l)).willReturn(taskMock);
        given(repository.save(taskMock)).willReturn(taskMock);

        service.register(taskMock);

        verify(domainService).checkBusinessRulesFor(taskMock);
        verify(repository).save(taskMock);
    }


    @Test
    public void shouldUpdateParent() {
        Task taskParent = TaskBuilder.createTaskBuilder(2l).build();
        Task task = TaskBuilder.createTaskBuilder(1l)
                .withParent(taskParent).build();
        Task taskDBMock = Mockito.mock(Task.class);
        Task taskDBParentMock = Mockito.mock(Task.class);

        given(repository.getOne(2l)).willReturn(Optional.of(taskDBParentMock));
        given(repository.findOne(1l)).willReturn(taskDBMock);
        given(repository.save(taskDBMock)).willReturn(taskDBMock);

        service.register(task);

        verify(taskDBParentMock).addChild(taskDBMock);
    }

    @Test
    public void shouldRegisterSubtask() {
        service = Mockito.spy(this.service);
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(3l).build();
        OperationRegion operationRegion = new OperationRegion(5l);

        Task taskParent = TaskBuilder.createTaskBuilder(2l)
                .withSalesOrder(salesOrder)
                .withRegion(operationRegion).build();

        Task taskChild = Mockito.mock(Task.class);
        Task taskDBParentMock = Mockito.mock(Task.class);
        Task taskSaved = Mockito.mock(Task.class);

        given(taskDBParentMock.getSalesOrder()).willReturn(salesOrder);
        given(taskDBParentMock.getRegion()).willReturn(operationRegion);
        given(repository.getOne(2l)).willReturn(Optional.of(taskDBParentMock));
        doReturn(taskSaved).when(service).register(taskChild);

        service.registerSubtask(taskParent, taskChild);

        verify(taskChild).setParent(taskDBParentMock);
        verify(taskChild).setSalesOrder(salesOrder);
        verify(taskChild).setRegion(operationRegion);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenParentTaskNotExist() {
        Task taskParent = TaskBuilder.createTaskBuilder(2l).build();
        Task taskChild = Mockito.mock(Task.class);


        given(repository.getOne(2l)).willReturn(Optional.empty());

        service.registerSubtask(taskParent, taskChild);
    }

    @Test
    public void shouldGenetareTaskByNewSalesOrder() throws Exception {
        SalesOrder salesOrderMock = Mockito.mock(SalesOrder.class);
        List<Task> tasks = Lists.newArrayList(mock(Task.class), mock(Task.class));

        given(this.repository.findBySalesOrder(salesOrderMock)).willReturn(Lists.newArrayList());
        given(processor.process(salesOrderMock)).willReturn(tasks);

        this.service.generateTaskByNewSalesOrder(salesOrderMock);

        verify(this.repository).save(tasks);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenNoEmptyListOnFindBySalesOrder() throws Exception {
        SalesOrder salesOrderMock = Mockito.mock(SalesOrder.class);
        List<Task> tasks = Lists.newArrayList(mock(Task.class), mock(Task.class));

        given(this.repository.findBySalesOrder(salesOrderMock)).willReturn(tasks);

        this.service.generateTaskByNewSalesOrder(salesOrderMock);
    }

    @Test
    public void shouldFindTasksBySalesOrder() {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(1l).build();
        List<Task> tasks = Lists.newArrayList(mock(Task.class), mock(Task.class));

        given(repository.findBySalesOrder(salesOrder)).willReturn(tasks);

        List<Task> result = this.service.findBySaleOrder(salesOrder);

        assertThat(result, sameInstance(tasks));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenInvalidSalesOrderOnFindTasksBySalesOrder() {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder().build();

        this.service.findBySaleOrder(salesOrder);
    }

    @Test
    public void shouldReturnTrueWhenIsSomeoneSon() {
        Task task = TaskBuilder.createTaskBuilder(2l).build();

        given(repository.isSomeonesSon(task)).willReturn(Boolean.TRUE);

        Boolean result = this.service.isSomeonesSon(task);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldFalseWhenInvalidTaskOnIsSomeoneSon() {
        Task task = TaskBuilder.createTaskBuilder().build();

        Boolean result = this.service.isSomeonesSon(task);

        verifyNoMoreInteractions(repository);
        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldChangeStatus() {
        UserEntity userChange = UserEntityBuilder.createUser(2l).build();
        Task taskParam = TaskBuilder.createTaskBuilder(1l)
                .withStatus(TaskStatus.DONE).build();

        Task taskDB = Mockito.mock(Task.class);

        given(repository.findOne(1l)).willReturn(taskDB);

        this.service.changeStatus(taskParam, userChange);

        verify(taskDB).setStatus(TaskStatus.DONE);
        verify(changeHistoryRepository).save(Mockito.any(TaskChangeHistory.class));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenTaskNoFoundONChangeStatus() {
        UserEntity userChange = UserEntityBuilder.createUser(2l).build();
        Task taskParam = TaskBuilder.createTaskBuilder(1l)
                .withStatus(TaskStatus.DONE).build();

        given(repository.findOne(1l)).willReturn(null);

        this.service.changeStatus(taskParam, userChange);
    }

    @Test
    public void shouldReturnStatisticsOfDeliveryExecutionTasks() {

        given(repository.countByStatus(TaskStatus.DONE)).willReturn(1l);
        given(repository.countByStatus(TaskStatus.STATED)).willReturn(2l);
        given(repository.countByStatus(TaskStatus.WAITING)).willReturn(3l);
        given(repository.countByStatus(TaskStatus.PROBLEM)).willReturn(4l);

        DeliveryResumeExecutionTaskDTO result = this.service.getResume();

        assertThat(result.getByName(TaskStatus.DONE.name()).getCount(), is(1l));
        assertThat(result.getByName(TaskStatus.STATED.name()).getCount(), is(2l));
        assertThat(result.getByName(TaskStatus.WAITING.name()).getCount(), is(3l));
        assertThat(result.getByName(TaskStatus.PROBLEM.name()).getCount(), is(4l));


    }

    @Test
    public void shouldReturnStatisticsOfDeliveryExecutionTasksBySalesOrder() {
        SalesOrder salesOrder = mock(SalesOrder.class);

        given(repository.countByStatus(TaskStatus.DONE, salesOrder)).willReturn(1l);
        given(repository.countByStatus(TaskStatus.STATED, salesOrder)).willReturn(2l);
        given(repository.countByStatus(TaskStatus.WAITING, salesOrder)).willReturn(3l);
        given(repository.countByStatus(TaskStatus.PROBLEM, salesOrder)).willReturn(4l);

        DeliveryResumeExecutionTaskDTO result = this.service.getResume(salesOrder);

        assertThat(result.getByName(TaskStatus.DONE.name()).getCount(), is(1l));
        assertThat(result.getByName(TaskStatus.STATED.name()).getCount(), is(2l));
        assertThat(result.getByName(TaskStatus.WAITING.name()).getCount(), is(3l));
        assertThat(result.getByName(TaskStatus.PROBLEM.name()).getCount(), is(4l));
    }

    @Test
    public void shouldCountBySalesOrder() {
        SalesOrder salesOrder = mock(SalesOrder.class);

        given(repository.countBySalesOrder(salesOrder)).willReturn(2l);

        Long result = this.service.countBySalesOrder(salesOrder);

        assertThat(result, is(2l));
    }

    @Test
    public void shouldReturnZeroWhenSalesOderNoId() {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder().build();

        Long result = this.service.countBySalesOrder(salesOrder);

        verifyNoMoreInteractions(repository);
        assertThat(result, is(0l));
    }

    @Test
    public void shouldSignedTask() {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Task task = TaskBuilder.createTaskBuilder(1l).build();
        Task taskDB = TaskBuilder.createTaskBuilder(1l).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskDB));

        this.service.signedTask(userMock, task);

        assertThat(taskDB.getSignedBy().size(), is(1));
        assertThat(taskDB.getSignedBy().contains(userMock), is(Boolean.TRUE));
    }

    @Test
    public void shouldNotSignedTaskWhenAlreadyExists() {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        Task task = TaskBuilder.createTaskBuilder(1l).build();
        Task taskDB = TaskBuilder
                .createTaskBuilder(1l).withSignedBy(Lists.newArrayList(userMock)).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskDB));

        this.service.signedTask(userMock, task);

        assertThat(taskDB.getSignedBy().size(), is(1));
    }

    @Test
    public void shouldUnsignedTask() {
        UserEntity userMock = UserEntityBuilder.createUser(3l).build();
        Task task = TaskBuilder.createTaskBuilder(1l).build();
        Task taskDB = TaskBuilder
                .createTaskBuilder(1l).withSignedBy(Lists.newArrayList(userMock)).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskDB));


        this.service.unsignedTask(userMock, task);

        assertThat(taskDB.getSignedBy().size(), is(0));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenTaskNoId() {
        UserEntity userMock = UserEntityBuilder.createUser(3l).build();
        Task task = TaskBuilder.createTaskBuilder().build();

        this.service.unsignedTask(userMock, task);

        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindtaskRootBySalesOrder() {
        SalesOrder salesOrder = SalesOrderBuilder.createSalesOrder(1l).build();

        given(this.repository.findTaskRootBy(salesOrder))
                .willReturn(Lists.newArrayList(mock(Task.class)));

        List<Task> result = this.service.findTaskRootBy(salesOrder);

        assertThat(result.size(), is(1));
    }
}
