package br.com.kproj.salesman.assistants.calendar.activities.specialization.infrastructure.persistence.springdata;


import br.com.kproj.salesman.infrastructure.entity.ContactEntity;
import br.com.kproj.salesman.infrastructure.repository.BaseRepositoryLegacy;
import org.springframework.stereotype.Repository;

@Repository("contactRepositoryCalendarModule")
public interface ContactEntityRepositorySpringData extends BaseRepositoryLegacy<ContactEntity, Long> {



}
