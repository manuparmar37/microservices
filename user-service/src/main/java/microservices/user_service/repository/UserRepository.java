package microservices.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import microservices.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
