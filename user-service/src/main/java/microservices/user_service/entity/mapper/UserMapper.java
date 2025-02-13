package microservices.user_service.entity.mapper;

import microservices.core_service.dto.UserDto;
import microservices.user_service.entity.User;

public class UserMapper {
    private UserMapper() {
    }

    public static User mapToUser(UserDto userDto) {
        return new User(userDto.id(), userDto.name());
    }

    public static UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName());
    }
}
