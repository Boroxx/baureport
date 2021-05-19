package com.boristenelsen.baureport.repository;

import com.boristenelsen.baureport.model.GehwegModulDao;
import com.boristenelsen.baureport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface    GehwegModulRepository  extends JpaRepository<GehwegModulDao ,Long> {
    GehwegModulDao findGehwegModulDaoByUserid(UUID uuid);

}
