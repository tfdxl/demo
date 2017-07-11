package com.example.repository;

import com.example.entity.Trace;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by tianfeng on 2017/7/10.
 */
@Repository
@Table(name = "fcm_car_trace")
@Qualifier("traceDao")
public interface TraceDao extends CrudRepository<Trace, Long> {
    Trace save(Trace s);
}
