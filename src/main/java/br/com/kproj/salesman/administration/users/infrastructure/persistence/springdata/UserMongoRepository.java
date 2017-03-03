package br.com.kproj.salesman.administration.users.infrastructure.persistence.springdata;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserMongoRepository extends MongoRepository<UserMongo, String> {


}
