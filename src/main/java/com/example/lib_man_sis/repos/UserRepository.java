package com.example.lib_man_sis.repos;

import com.example.lib_man_sis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByLibraryIdAndPassword(long libraryId, String password);
}
