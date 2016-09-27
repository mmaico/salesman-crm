package br.com.kproj.salesman.auditing.delivery.application;



import br.com.kproj.salesman.auditing.delivery.domain.model.audit.Auditing;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.AuditingRepository;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.LastVersion;
import br.com.kproj.salesman.auditing.delivery.domain.model.audit.NewAuditingRequest;
import br.com.kproj.salesman.auditing.delivery.domain.model.task.Task;
import br.com.kproj.salesman.auditing.delivery.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskAuditingServiceImpl implements TaskAuditingFacade {

    private AuditingRepository repository;


    @Autowired
    public TaskAuditingServiceImpl(AuditingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(NewAuditingRequest request) {
        User user = request.getUser();
        Task task = request.getTask();
        String currentVersion = task.getCurrentVersion();

        Optional<Auditing> lastVersionAudit = repository.findOne(LastVersion.of(task));

        Boolean wasChanged = task.wasChanged().whenComparedWith(lastVersionAudit);

        if (wasChanged) {
            user.changed(task).andTheNewVersionIs(currentVersion);
        }
    }
}
