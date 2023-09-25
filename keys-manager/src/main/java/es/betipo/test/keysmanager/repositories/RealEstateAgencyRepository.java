package es.betipo.test.keysmanager.repositories;

import es.betipo.test.keysmanager.entities.RealEstateAgencyEntity;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactorCrudRepository;
import io.micronaut.data.repository.reactive.ReactorPageableRepository;

@R2dbcRepository(dialect = Dialect.MYSQL)
public interface RealEstateAgencyRepository extends ReactorPageableRepository<RealEstateAgencyEntity, Long>, ReactorCrudRepository<RealEstateAgencyEntity, Long> {
}