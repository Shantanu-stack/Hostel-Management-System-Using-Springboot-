package com.pgmanagement.hostels.repository;

import com.pgmanagement.hostels.entity.HostelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository<HostelEntity,Long> {


}
