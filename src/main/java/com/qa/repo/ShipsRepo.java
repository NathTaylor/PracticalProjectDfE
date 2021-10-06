package com.qa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.domain.Ships;

@Repository
public interface ShipsRepo extends JpaRepository<Ships, Long>{

}
