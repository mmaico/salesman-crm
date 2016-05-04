package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.auditing.TaskAudinting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskAuditingRepository extends BaseRepository<TaskAudinting, Long> {



    @Query("SELECT ta FROM TaskAudinting AS ta WHERE ta.entityId = :entityId ORDER BY ta.lastUpdate desc ")
    Page<TaskAudinting> findLasVersion(@Param("entityId") Long entityId, Pageable pageable);

    @Query("SELECT ta FROM TaskAudinting AS ta WHERE ta.entityId = :entityId ORDER BY ta.lastUpdate desc ")
    Page<TaskAudinting> findAll(@Param("entityId") Long entityId, Pageable pageable);

}
