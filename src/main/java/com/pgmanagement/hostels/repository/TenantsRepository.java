package com.pgmanagement.hostels.repository;

import com.pgmanagement.hostels.entity.Tenantsentity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.pgmanagement.hostels.entity.Roomentity;
public interface TenantsRepository extends JpaRepository<Tenantsentity,Long> {
    List<Tenantsentity> findByRoom(Roomentity room);

}
