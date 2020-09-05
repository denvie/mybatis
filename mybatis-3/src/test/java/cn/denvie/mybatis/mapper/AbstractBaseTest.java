/*
 * Copyright © 2020-2020 尛飛俠（Denvie） All rights reserved.
 */

package cn.denvie.mybatis.mapper;

import cn.denvie.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

/**
 * AbstractBaseTest。
 *
 * @author denvie
 * @since 2020/9/5
 */
public abstract class AbstractBaseTest {
    protected SqlSession session;

    @Before
    public void setUp() {
        session = MyBatisUtils.getSqlSession();
    }

    @After
    public void tearDown() {
        if (session != null) {
            session.close();
        }
    }
}
