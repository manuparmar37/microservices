package microservices.user_service.service.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import microservices.core_service.dto.UserDto;
import microservices.user_service.entity.User;
import microservices.user_service.entity.mapper.UserMapper;
import microservices.user_service.repository.UserRepository;
import microservices.user_service.service.UserService;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).toList();
    }

}
