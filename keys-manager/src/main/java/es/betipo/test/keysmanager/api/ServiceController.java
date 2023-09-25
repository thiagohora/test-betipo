package es.betipo.test.keysmanager.api;

import es.betipo.test.keysmanager.api.exceptions.NotFoundException;
import es.betipo.test.keysmanager.api.mappers.ServiceMapper;
import es.betipo.test.keysmanager.entities.ServiceEntity;
import es.betipo.test.keysmanager.model.Service;
import es.betipo.test.keysmanager.model.ServicesGet200Response;
import es.betipo.test.keysmanager.repositories.ServiceRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ServiceController implements ServicesApi {

    private static ServicesGet200Response mapService(final List<ServiceEntity> services) {
        final var response = new ServicesGet200Response();
        response.setServices(services.stream().map(ServiceMapper.MAPPER::map).toList());
        return response;
    }

    private final ServiceRepository serviceRepository;

    @Override
    public Mono<ServicesGet200Response> servicesGet() {
        return serviceRepository.findAll()
                .collectList()
                .map(ServiceController::mapService);
    }

    @Override
    public Mono<HttpResponse<Void>> servicesIdDelete(Long id) {
        return serviceRepository.deleteById(id)
                .map(aVoid -> HttpResponse.noContent());
    }

    @Override
    public Mono<Service> servicesIdGet(Long id) {
        return serviceRepository.findById(id)
                .map(ServiceMapper.MAPPER::map)
                .switchIfEmpty(Mono.error(new NotFoundException("Service not found")));
    }

    @Override
    public Mono<HttpResponse<Void>> servicesIdPut(Long id, Service service) {
        return serviceRepository.findById(id)
                .flatMap(it -> {
                    ServiceMapper.MAPPER.update(it, service);
                    return serviceRepository.save(it);
                })
                .switchIfEmpty(Mono.error(new NotFoundException("Service not found")))
                .map(aVoid -> HttpResponse.noContent());
    }

    @Override
    public Mono<HttpResponse<Service>> servicesPost(Service service) {
        return serviceRepository.save(ServiceMapper.MAPPER.map(service))
                .map(ServiceMapper.MAPPER::map)
                .map(HttpResponse::created);
    }

}
