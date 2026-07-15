package org.studentmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, String> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumberAndIdNot(
            String phoneNumber,
            String id
    );

    boolean existsByEmailAndIdNot(
            String email,
            String id
    );
}