package br.com.kproj.salesman.infrastructure.repository;


import br.com.kproj.salesman.infrastructure.entity.location.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends BaseRepository<City, Long> {

    @Query("SELECT c FROM City AS c WHERE c.stateName = :state ORDER BY c.name ASC")
    public List<City> getCityByState(@Param("state")String state);

}
