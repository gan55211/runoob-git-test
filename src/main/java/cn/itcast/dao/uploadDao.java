package cn.itcast.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.itcast.domain.quyu;
@Repository
public interface uploadDao extends JpaRepository<quyu, String>{

}
