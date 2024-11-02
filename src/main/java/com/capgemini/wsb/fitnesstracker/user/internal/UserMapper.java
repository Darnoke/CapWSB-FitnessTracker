package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;


@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    UserIdEmailDto toIdEmailDto(User user) {

        return new UserIdEmailDto(
                user.getId(),
                user.getEmail());
    }

    User toUpdateEntity(UserDto userDto, User user) {
        user.setFirstName(userDto.firstName() != null ? userDto.firstName() : user.getFirstName());
        user.setLastName(userDto.lastName() != null ? userDto.lastName() : user.getLastName());
        user.setBirthdate(userDto.birthdate() != null ? userDto.birthdate() : user.getBirthdate());
        user.setEmail(userDto.email() != null ? userDto.email() : user.getEmail());

        return user;
    }

}
