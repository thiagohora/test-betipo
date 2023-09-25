package es.betipo.test.keysmanager.api;

import es.betipo.test.keysmanager.api.exceptions.NotFoundException;
import es.betipo.test.keysmanager.api.mappers.RealStateAgencyMapper;
import es.betipo.test.keysmanager.entities.RealEstateAgencyEntity;
import es.betipo.test.keysmanager.model.RealEstateAgenciesGet200Response;
import es.betipo.test.keysmanager.model.RealEstateAgency;
import es.betipo.test.keysmanager.model.RealEstatePropertiesGet200Response;
import es.betipo.test.keysmanager.model.RealEstateProperty;
import es.betipo.test.keysmanager.repositories.RealEstateAgencyRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RealEstateAgencyController implements AgenciesApi {

    private static RealEstateAgenciesGet200Response mapService(final List<RealEstateAgencyEntity> agencies) {
        final var response = new RealEstateAgenciesGet200Response();
        response.setRealStateAgencies(agencies.stream().map(RealStateAgencyMapper.MAPPER::map).toList());
        return response;
    }

    private final RealEstateAgencyRepository repository;

    @Override
    public Mono<RealEstateAgenciesGet200Response> realEstateAgenciesGet() {
        return repository.findAll()
                .collectList()
                .map(RealEstateAgencyController::mapService);
    }

    @Override
    public Mono<HttpResponse<Void>> realEstateAgenciesIdDelete(Long id) {
        return repository.deleteById(id)
                .map(aVoid -> HttpResponse.noContent());
    }

    @Override
    public Mono<RealEstateAgency> realEstateAgenciesIdGet(Long id) {
        return repository.findById(id)
                .map(RealStateAgencyMapper.MAPPER::map)
                .switchIfEmpty(Mono.error(new NotFoundException("Real Estate Agency not found")));
    }

    @Override
    public Mono<HttpResponse<Void>> realEstateAgenciesIdPut(Long id, RealEstateAgency realEstateAgency) {
        return repository.findById(id)
                .flatMap(it -> {
                    RealStateAgencyMapper.MAPPER.update(it, realEstateAgency);
                    return repository.save(it);
                })
                .switchIfEmpty(Mono.error(new NotFoundException("Real Estate Agency not found")))
                .map(aVoid -> HttpResponse.noContent());
    }

    @Override
    public Mono<HttpResponse<RealEstateAgency>> realEstateAgenciesPost(RealEstateAgency realEstateAgency) {
        return repository.save(RealStateAgencyMapper.MAPPER.map(realEstateAgency))
                .map(RealStateAgencyMapper.MAPPER::map)
                .map(HttpResponse::created);
    }

    @Override
    public Mono<RealEstatePropertiesGet200Response> realEstatePropertiesGet() {
        return null;
    }

    @Override
    public Mono<HttpResponse<Void>> realEstatePropertiesIdDelete(Long id) {
        return null;
    }

    @Override
    public Mono<RealEstateProperty> realEstatePropertiesIdGet(Long id) {
        return null;
    }

    @Override
    public Mono<HttpResponse<Void>> realEstatePropertiesIdPut(Long id, RealEstateProperty realEstateProperty) {
        return null;
    }

    @Override
    public Mono<HttpResponse<RealEstateProperty>> realEstatePropertiesPost(RealEstateProperty realEstateProperty) {
        return null;
    }
}