package com.jpa.user1984.repository;


import com.jpa.user1984.domain.Store;
import com.jpa.user1984.dto.StoreDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
