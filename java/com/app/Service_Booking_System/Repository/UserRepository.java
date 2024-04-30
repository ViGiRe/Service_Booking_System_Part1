package com.app.Service_Booking_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Service_Booking_System.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findFirstByEmail(String email);
}
