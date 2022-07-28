package com.example.sushi.repository.admin;

import com.example.sushi.entity.admin.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, String> {

}
