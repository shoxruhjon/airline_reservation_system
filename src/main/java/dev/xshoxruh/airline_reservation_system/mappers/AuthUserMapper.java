package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AuthUserCreateDto;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);

    AuthUserCreateDto toDto(AuthUser authUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AuthUser partialUpdate(AuthUserCreateDto authUserCreateDto, @MappingTarget AuthUser authUser);
}