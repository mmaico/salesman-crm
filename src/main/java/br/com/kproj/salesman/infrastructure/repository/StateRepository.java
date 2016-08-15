package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.location.State;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends BaseRepositoryLegacy<State, Long> {

    @Query("SELECT s FROM State AS s WHERE s.id <> 1 ORDER BY s.name ASC")
    List<State> getNacionalStates();

    @Query("SELECT s FROM State AS s WHERE s.id = 1")
    List<State> getInternacionalStates();

}
