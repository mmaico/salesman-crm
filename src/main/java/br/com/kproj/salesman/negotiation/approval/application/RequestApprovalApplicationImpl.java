package br.com.kproj.salesman.negotiation.approval.application;

import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.RequestApproval;
import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import br.com.kproj.salesman.infrastructure.repository.RequestApprovalRepository;
import br.com.kproj.salesman.infrastructure.service.BaseModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestApprovalApplicationImpl extends BaseModelServiceImpl<RequestApproval> implements RequestApprovalApplication {

    @Autowired
    private RequestApprovalRepository repository;




    @Override
    public RequestApproval register(RequestApproval requestApproval) {
        return null;
    }

    @Override
    public BaseRepository<RequestApproval, Long> getRepository() {
        return repository;
    }
}
