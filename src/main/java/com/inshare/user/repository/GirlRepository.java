package com.inshare.user.repository;

import com.inshare.user.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl, Integer> {

    public List<Girl> findByNameContaining(String name);
}
