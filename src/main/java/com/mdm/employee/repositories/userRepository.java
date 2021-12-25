package com.mdm.employee.repositories;

import com.mdm.employee.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface userRepository  extends JpaRepository<user, Long> {
    user findByEmail(String userId);

    user findByPhoneno(Long phoneNo);
}
