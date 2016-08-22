package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.UserEntity;
import br.com.kproj.salesman.infrastructure.entity.proposal.requestapproval.ApproverProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApproverProfileRepository extends BaseRepositoryLegacy<ApproverProfile, Long> {


    Optional<ApproverProfile> findByApprover(@Param("approver") UserEntity approver);

    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM ApproverProfile AS ap WHERE ap.available is true ")
    Boolean hasApprovers();

    @Query("SELECT " +
            " CASE WHEN count(*) > 0 " +
            "      THEN true " +
            "      ELSE false " +
            " END " +
            "FROM ApproverProfile AS ap WHERE ap.available is true AND ap.approver <> :user ")
    Boolean hasApproversExcludeParam(@Param("user") UserEntity user);

}
