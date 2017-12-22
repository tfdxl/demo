package com.example.repository;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @author tianfeng
 * @date 2017/5/30
 * 不用去写实现类，spring会帮我们实现
 */
@Repository
@Table(name = "user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * findOne
     *
     * @param id
     * @return
     */
    @Query("select t from User t where t.id=:id")
    User findOne(@Param("id") Long id);

    /**
     * 保存方法
     *
     * @param user
     * @return
     */
    @Override
    User save(User user);

    /**
     * findUserByName
     *
     * @param name
     * @return
     */
    //采用的是hql的查询方式？
    @Query("select t from User t where t.userName=:name")
    User findUserByName(@Param("name") String name);

    /**
     * findByUsernameAndPwd
     *
     * @param name
     * @param password
     * @return
     */
    @Query("select t from User t where t.userName=:name and t.password=:password")
    User findByUsernameAndPwd(@Param("name") String name, @Param("password") String password);
}
