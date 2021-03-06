package com.tujia.com.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tujia.com.dubbo.api.po.TUser0000;
import com.tujia.com.dubbo.api.po.TUser0000Example;
import com.tujia.comdubboapi.dao.TUser0000Dao;
import com.tujia.comdubboapi.service.TUser0000Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述:
 *    用户操纵Service
 * @author zhouheng
 * @create 2019-03-28 下午 3:22
 */
@Service(version = "1.0.0", group = "TUser0000Service", timeout = 5000,protocol = "dubbo")
public class TUser0000ServiceImpl implements TUser0000Service {

    @Autowired
    private TUser0000Dao tUser0000Dao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int i = tUser0000Dao.deleteByPrimaryKey(id);
        int i1 = 10 / 0;
        return i;
    }

    @Override
    public int insert(TUser0000 record) {
        return tUser0000Dao.insert(record);
    }

    @Override
    public int insertSelective(TUser0000 record) {
        return tUser0000Dao.insertSelective(record);
    }

    @Override
    public List<TUser0000> selectByExample(TUser0000Example example) {
        return tUser0000Dao.selectByExample(example);
    }

    @Override
    public TUser0000 selectByPrimaryKey(Integer id) {
        return tUser0000Dao.selectByPrimaryKey(id);
    }
}
