package es.betipo.test.keysmanager.repositories;

import es.betipo.test.keysmanager.entities.ServiceEntity;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface ServiceRepository extends ReactorPageableRepository<ServiceEntity, Long>, ReactorCrudRepository<ServiceEntity, Long> {
}