package br.com.kproj.salesman.administration.approval_negotiation.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApproverEntityRepository extends BaseRepositoryLegacy<ApproverEntity, Long> {


    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM ApproverEntity AS a WHERE a.available = 'TRUE'")
    Boolean hasApprovers();

    @Query("SELECT a FROM ApproverEntity AS a WHERE a.available = 'TRUE'")
    List<ApproverEntity> getApprovers();


}
