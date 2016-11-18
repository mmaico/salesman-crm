package br.com.kproj.salesman.delivery.tasks.domain.model.tasks.roottask;


import br.com.kproj.salesman.infrastructure.repository.BaseRepository;


public interface RootTaskRepository extends BaseRepository<RootTask, Long> {

    void delete(RootTask task);


}
