package com.mgk.repository;

import java.util.Optional;

import com.mgk.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	Optional<SiteUser> findByusername(String username);
}