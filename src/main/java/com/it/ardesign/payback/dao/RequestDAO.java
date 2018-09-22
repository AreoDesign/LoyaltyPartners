package com.it.ardesign.payback.dao;

import com.it.ardesign.payback.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDAO extends JpaRepository<Request, Integer> {
}
