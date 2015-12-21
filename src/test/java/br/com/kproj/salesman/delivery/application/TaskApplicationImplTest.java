package br.com.kproj.salesman.delivery.application;

import br.com.kproj.salesman.delivery.domain.TaskDomainService;
import br.com.kproj.salesman.infrastructure.entity.task.Task;
import br.com.kproj.salesman.infrastructure.repository.task.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskApplicationImplTest {

    @InjectMocks
    private TaskApplicationImpl service;

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskDomainService domainService;


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


}