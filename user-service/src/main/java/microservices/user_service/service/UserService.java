package microservices.user_service.service;

import java.util.List;

import microservices.core_service.dto.UserDto;

public interface UserService {
    public List<UserDto> getUsers();
}
