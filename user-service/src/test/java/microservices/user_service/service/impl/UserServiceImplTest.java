package microservices.user_service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservices.core_service.dto.UserDto;
import microservices.user_service.entity.User;
import microservices.user_service.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testGetUsers() {
        when(userRepository.findAll())
                .thenReturn(List.of(new User(1L, "User 1"), new User(2L, "User 2")));

        List<UserDto> users = userService.getUsers();

        assertNotNull(users);
        assertEquals(2, users.size());
        assertEquals(1L, users.get(0).id());
        assertEquals("User 1", users.get(0).name());
        assertEquals(2L, users.get(1).id());
        assertEquals("User 2", users.get(1).name());
    }
}
