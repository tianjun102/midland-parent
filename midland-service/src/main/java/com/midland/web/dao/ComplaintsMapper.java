package com.midland.web.dao;

import com.midland.web.model.Complaints;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ComplaintsMapper {

    Complaints selectComplaintsById(Integer complaints);

    int deleteComplaintsById(Integer complaints);

    int updateComplaintsById(Complaints complaints);

    int insertComplaints(Complaints complaints);

    List<Complaints> findComplaintsList(Complaints complaints);

}
