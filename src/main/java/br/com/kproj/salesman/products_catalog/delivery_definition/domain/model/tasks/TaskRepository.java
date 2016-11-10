package br.com.kproj.salesman.products_catalog.delivery_definition.domain.model.tasks;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends BaseRepository<Task, Long> {

    Page<Task> findAll(Pageable pageable);


}
