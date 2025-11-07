package com.briup.smartlabs.gateway.dao;

import com.briup.smartlabs.gateway.bean.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvDao extends JpaRepository<Environment, String> {

}
