package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;
import org.springframework.data.domain.Pageable;


public interface RootTaskRepository extends BaseRepository<RootTask, Long> {

    void delete(RootTask task);

    Iterable<RootTask> findAll(Long deliveryId, Pageable pageable);


}
