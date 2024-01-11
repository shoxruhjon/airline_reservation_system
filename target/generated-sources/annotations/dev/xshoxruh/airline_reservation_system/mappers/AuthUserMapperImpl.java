package dev.xshoxruh.airline_reservation_system.mappers;

import dev.xshoxruh.airline_reservation_system.dtos.auth.AuthUserCreateDto;
import dev.xshoxruh.airline_reservation_system.entities.AuthUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-07T14:06:03+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class AuthUserMapperImpl implements AuthUserMapper {

    @Override
    public AuthUser toEntity(AuthUserCreateDto authUserCreateDto) {
        if ( authUserCreateDto == null ) {
            return null;
        }

        AuthUser.AuthUserBuilder authUser = AuthUser.builder();

        authUser.username( authUserCreateDto.getUsername() );
        authUser.email( authUserCreateDto.getEmail() );
        authUser.firstName( authUserCreateDto.getFirstName() );
        authUser.lastName( authUserCreateDto.getLastName() );
        authUser.dateOfBirth( authUserCreateDto.getDateOfBirth() );
        authUser.password( authUserCreateDto.getPassword() );
        authUser.citizenship( authUserCreateDto.getCitizenship() );
        authUser.gender( authUserCreateDto.getGender() );

        return authUser.build();
    }

    @Override
    public AuthUserCreateDto toDto(AuthUser authUser) {
        if ( authUser == null ) {
            return null;
        }

        AuthUserCreateDto authUserCreateDto = new AuthUserCreateDto();

        authUserCreateDto.setUsername( authUser.getUsername() );
        authUserCreateDto.setEmail( authUser.getEmail() );
        authUserCreateDto.setFirstName( authUser.getFirstName() );
        authUserCreateDto.setLastName( authUser.getLastName() );
        authUserCreateDto.setDateOfBirth( authUser.getDateOfBirth() );
        authUserCreateDto.setPassword( authUser.getPassword() );
        authUserCreateDto.setCitizenship( authUser.getCitizenship() );
        authUserCreateDto.setGender( authUser.getGender() );

        return authUserCreateDto;
    }

    @Override
    public AuthUser partialUpdate(AuthUserCreateDto authUserCreateDto, AuthUser authUser) {
        if ( authUserCreateDto == null ) {
            return authUser;
        }

        if ( authUserCreateDto.getUsername() != null ) {
            authUser.setUsername( authUserCreateDto.getUsername() );
        }
        if ( authUserCreateDto.getEmail() != null ) {
            authUser.setEmail( authUserCreateDto.getEmail() );
        }
        if ( authUserCreateDto.getFirstName() != null ) {
            authUser.setFirstName( authUserCreateDto.getFirstName() );
        }
        if ( authUserCreateDto.getLastName() != null ) {
            authUser.setLastName( authUserCreateDto.getLastName() );
        }
        if ( authUserCreateDto.getDateOfBirth() != null ) {
            authUser.setDateOfBirth( authUserCreateDto.getDateOfBirth() );
        }
        if ( authUserCreateDto.getPassword() != null ) {
            authUser.setPassword( authUserCreateDto.getPassword() );
        }
        if ( authUserCreateDto.getCitizenship() != null ) {
            authUser.setCitizenship( authUserCreateDto.getCitizenship() );
        }
        if ( authUserCreateDto.getGender() != null ) {
            authUser.setGender( authUserCreateDto.getGender() );
        }

        return authUser;
    }
}
