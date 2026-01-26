package org.example.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.dao.po.NotifyTask;

@Mapper
public interface INotifyTaskDao {
    void insert(NotifyTask notifyTask);
}
