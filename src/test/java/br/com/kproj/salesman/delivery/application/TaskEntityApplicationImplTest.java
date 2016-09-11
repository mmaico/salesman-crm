package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.application.tasks.TaskApplicationImpl;
import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.delivery.infrastructure.dtos.DeliveryResumeExecutionTaskDTO;
import br.com.kproj.salesman.delivery.infrastructure.generatebysalesorder.SalesOrderTaskItemProcessor;
import br.com.kproj.salesman.delivery.infrastructure.repository.TaskChangeHistoryRepository;
import br.com.kproj.salesman.infrastructure.entity.OperationRegionEntity;
import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.builders.SalesOrderBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.TaskBuilder;
import br.com.kproj.salesman.infrastructure.entity.builders.UserEntityBuilder;
import br.com.kproj.salesman.infrastructure.entity.enums.TaskStatus;
import br.com.kproj.salesman.infrastructure.entity.sale.SalesOrderEntity;
import br.com.kproj.salesman.infrastructure.entity.task.TaskEntity;
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
public class TaskEntityApplicationImplTest {

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
        TaskEntity taskEntityMock = Mockito.mock(TaskEntity.class);

        given(taskEntityMock.isNew()).willReturn(Boolean.TRUE);
        given(repository.save(taskEntityMock)).willReturn(taskEntityMock);
        service.register(taskEntityMock);

        verify(domainService).checkBusinessRulesFor(taskEntityMock);
        verify(domainService).prepareToSave(taskEntityMock);
    }

    @Test
    public void shouldUpdateATask() {
        TaskEntity taskEntityMock = Mockito.mock(TaskEntity.class);

        given(taskEntityMock.getId()).willReturn(1l);
        given(taskEntityMock.isNew()).willReturn(Boolean.FALSE);
        given(repository.findOne(1l)).willReturn(taskEntityMock);
        given(repository.save(taskEntityMock)).willReturn(taskEntityMock);

        service.register(taskEntityMock);

        verify(domainService).checkBusinessRulesFor(taskEntityMock);
        verify(repository).save(taskEntityMock);
    }


    @Test
    public void shouldUpdateParent() {
        TaskEntity taskEntityParent = TaskBuilder.createTaskBuilder(2l).build();
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder(1l)
                .withParent(taskEntityParent).build();
        TaskEntity taskEntityDBMock = Mockito.mock(TaskEntity.class);
        TaskEntity taskEntityDBParentMock = Mockito.mock(TaskEntity.class);

        given(repository.getOne(2l)).willReturn(Optional.of(taskEntityDBParentMock));
        given(repository.findOne(1l)).willReturn(taskEntityDBMock);
        given(repository.save(taskEntityDBMock)).willReturn(taskEntityDBMock);

        service.register(taskEntity);

        verify(taskEntityDBParentMock).addChild(taskEntityDBMock);
    }

    @Test
    public void shouldRegisterSubtask() {
        service = Mockito.spy(this.service);
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder(3l).build();
        OperationRegionEntity operationRegionEntity = new OperationRegionEntity(5l);

        TaskEntity taskEntityParent = TaskBuilder.createTaskBuilder(2l)
                .withSalesOrder(salesOrderEntity)
                .withRegion(operationRegionEntity).build();

        TaskEntity taskEntityChild = Mockito.mock(TaskEntity.class);
        TaskEntity taskEntityDBParentMock = Mockito.mock(TaskEntity.class);
        TaskEntity taskEntitySaved = Mockito.mock(TaskEntity.class);

        given(taskEntityDBParentMock.getSalesOrderEntity()).willReturn(salesOrderEntity);
        given(taskEntityDBParentMock.getRegion()).willReturn(operationRegionEntity);
        given(repository.getOne(2l)).willReturn(Optional.of(taskEntityDBParentMock));
        doReturn(taskEntitySaved).when(service).register(taskEntityChild);

        service.registerSubtask(taskEntityParent, taskEntityChild);

        verify(taskEntityChild).setParent(taskEntityDBParentMock);
        verify(taskEntityChild).setSalesOrderEntity(salesOrderEntity);
        verify(taskEntityChild).setRegion(operationRegionEntity);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenParentTaskNotExist() {
        TaskEntity taskEntityParent = TaskBuilder.createTaskBuilder(2l).build();
        TaskEntity taskEntityChild = Mockito.mock(TaskEntity.class);


        given(repository.getOne(2l)).willReturn(Optional.empty());

        service.registerSubtask(taskEntityParent, taskEntityChild);
    }

    @Test
    public void shouldGenetareTaskByNewSalesOrder() throws Exception {
        SalesOrderEntity salesOrderEntityMock = Mockito.mock(SalesOrderEntity.class);
        List<TaskEntity> taskEntities = Lists.newArrayList(mock(TaskEntity.class), mock(TaskEntity.class));

        given(this.repository.findBySalesOrder(salesOrderEntityMock)).willReturn(Lists.newArrayList());
        given(processor.process(salesOrderEntityMock)).willReturn(taskEntities);

        this.service.generateTaskByNewSalesOrder(salesOrderEntityMock);

        verify(this.repository).save(taskEntities);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenNoEmptyListOnFindBySalesOrder() throws Exception {
        SalesOrderEntity salesOrderEntityMock = Mockito.mock(SalesOrderEntity.class);
        List<TaskEntity> taskEntities = Lists.newArrayList(mock(TaskEntity.class), mock(TaskEntity.class));

        given(this.repository.findBySalesOrder(salesOrderEntityMock)).willReturn(taskEntities);

        this.service.generateTaskByNewSalesOrder(salesOrderEntityMock);
    }

    @Test
    public void shouldFindTasksBySalesOrder() {
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder(1l).build();
        List<TaskEntity> taskEntities = Lists.newArrayList(mock(TaskEntity.class), mock(TaskEntity.class));

        given(repository.findBySalesOrder(salesOrderEntity)).willReturn(taskEntities);

        List<TaskEntity> result = this.service.findBySaleOrder(salesOrderEntity);

        assertThat(result, sameInstance(taskEntities));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenInvalidSalesOrderOnFindTasksBySalesOrder() {
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder().build();

        this.service.findBySaleOrder(salesOrderEntity);
    }

    @Test
    public void shouldReturnTrueWhenIsSomeoneSon() {
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder(2l).build();

        given(repository.isSomeonesSon(taskEntity)).willReturn(Boolean.TRUE);

        Boolean result = this.service.isSomeonesSon(taskEntity);

        assertThat(result, is(Boolean.TRUE));
    }

    @Test
    public void shouldFalseWhenInvalidTaskOnIsSomeoneSon() {
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder().build();

        Boolean result = this.service.isSomeonesSon(taskEntity);

        verifyNoMoreInteractions(repository);
        assertThat(result, is(Boolean.FALSE));
    }

    @Test
    public void shouldChangeStatus() {
        UserEntity userChange = UserEntityBuilder.createUser(2l).build();
        TaskEntity taskEntityParam = TaskBuilder.createTaskBuilder(1l)
                .withStatus(TaskStatus.DONE).build();

        TaskEntity taskEntityDB = Mockito.mock(TaskEntity.class);

        given(repository.findOne(1l)).willReturn(taskEntityDB);

        this.service.changeStatus(taskEntityParam, userChange);

        verify(taskEntityDB).setStatus(TaskStatus.DONE);
        verify(changeHistoryRepository).save(Mockito.any(TaskChangeHistory.class));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenTaskNoFoundONChangeStatus() {
        UserEntity userChange = UserEntityBuilder.createUser(2l).build();
        TaskEntity taskEntityParam = TaskBuilder.createTaskBuilder(1l)
                .withStatus(TaskStatus.DONE).build();

        given(repository.findOne(1l)).willReturn(null);

        this.service.changeStatus(taskEntityParam, userChange);
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
        SalesOrderEntity salesOrderEntity = mock(SalesOrderEntity.class);

        given(repository.countByStatus(TaskStatus.DONE, salesOrderEntity)).willReturn(1l);
        given(repository.countByStatus(TaskStatus.STATED, salesOrderEntity)).willReturn(2l);
        given(repository.countByStatus(TaskStatus.WAITING, salesOrderEntity)).willReturn(3l);
        given(repository.countByStatus(TaskStatus.PROBLEM, salesOrderEntity)).willReturn(4l);

        DeliveryResumeExecutionTaskDTO result = this.service.getResume(salesOrderEntity);

        assertThat(result.getByName(TaskStatus.DONE.name()).getCount(), is(1l));
        assertThat(result.getByName(TaskStatus.STATED.name()).getCount(), is(2l));
        assertThat(result.getByName(TaskStatus.WAITING.name()).getCount(), is(3l));
        assertThat(result.getByName(TaskStatus.PROBLEM.name()).getCount(), is(4l));
    }

    @Test
    public void shouldCountBySalesOrder() {
        SalesOrderEntity salesOrderEntity = mock(SalesOrderEntity.class);

        given(repository.countBySalesOrder(salesOrderEntity)).willReturn(2l);

        Long result = this.service.countBySalesOrder(salesOrderEntity);

        assertThat(result, is(2l));
    }

    @Test
    public void shouldReturnZeroWhenSalesOderNoId() {
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder().build();

        Long result = this.service.countBySalesOrder(salesOrderEntity);

        verifyNoMoreInteractions(repository);
        assertThat(result, is(0l));
    }

    @Test
    public void shouldSignedTask() {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder(1l).build();
        TaskEntity taskEntityDB = TaskBuilder.createTaskBuilder(1l).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskEntityDB));

        this.service.signedTask(userMock, taskEntity);

        assertThat(taskEntityDB.getSignedBy().size(), is(1));
        assertThat(taskEntityDB.getSignedBy().contains(userMock), is(Boolean.TRUE));
    }

    @Test
    public void shouldNotSignedTaskWhenAlreadyExists() {
        UserEntity userMock = Mockito.mock(UserEntity.class);
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder(1l).build();
        TaskEntity taskEntityDB = TaskBuilder
                .createTaskBuilder(1l).withSignedBy(Lists.newArrayList(userMock)).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskEntityDB));

        this.service.signedTask(userMock, taskEntity);

        assertThat(taskEntityDB.getSignedBy().size(), is(1));
    }

    @Test
    public void shouldUnsignedTask() {
        UserEntity userMock = UserEntityBuilder.createUser(3l).build();
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder(1l).build();
        TaskEntity taskEntityDB = TaskBuilder
                .createTaskBuilder(1l).withSignedBy(Lists.newArrayList(userMock)).build();

        given(repository.getOne(1l)).willReturn(Optional.of(taskEntityDB));


        this.service.unsignedTask(userMock, taskEntity);

        assertThat(taskEntityDB.getSignedBy().size(), is(0));
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenTaskNoId() {
        UserEntity userMock = UserEntityBuilder.createUser(3l).build();
        TaskEntity taskEntity = TaskBuilder.createTaskBuilder().build();

        this.service.unsignedTask(userMock, taskEntity);

        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindtaskRootBySalesOrder() {
        SalesOrderEntity salesOrderEntity = SalesOrderBuilder.createSalesOrder(1l).build();

        given(this.repository.findTaskRootBy(salesOrderEntity))
                .willReturn(Lists.newArrayList(mock(TaskEntity.class)));

        List<TaskEntity> result = this.service.findTaskRootBy(salesOrderEntity);

        assertThat(result.size(), is(1));
    }
}
