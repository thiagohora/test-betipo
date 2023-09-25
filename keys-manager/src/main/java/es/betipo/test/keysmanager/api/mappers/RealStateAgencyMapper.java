package es.betipo.test.keysmanager.api.mappers;

import es.betipo.test.keysmanager.entities.Address;
import es.betipo.test.keysmanager.entities.RealEstateAgencyEntity;
import es.betipo.test.keysmanager.entities.ServiceEntity;
import es.betipo.test.keysmanager.model.RealEstateAgency;
import es.betipo.test.keysmanager.model.Service;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RealStateAgencyMapper {

    RealStateAgencyMapper MAPPER = Mappers.getMapper( RealStateAgencyMapper.class );

    RealEstateAgencyEntity map(RealEstateAgency service);

    @InheritInverseConfiguration(name = "map")
    RealEstateAgency map(RealEstateAgencyEntity entity);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget RealEstateAgencyEntity entity, RealEstateAgency updateEntity);

    @Mapping(target = "latitude", source = "coordinates.latitude")
    @Mapping(target = "longitude", source = "coordinates.longitude")
    Address map(es.betipo.test.keysmanager.model.Address address);

    @InheritInverseConfiguration(name = "map")
    es.betipo.test.keysmanager.model.Address map(Address address);

}
