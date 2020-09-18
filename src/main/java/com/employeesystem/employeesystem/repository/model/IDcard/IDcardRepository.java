package com.employeesystem.employeesystem.repository.model.IDcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IDcardRepository extends JpaRepository<IDcard, String> {
}
