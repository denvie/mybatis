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
 * @author Denvie
 * @since 2020/9/5
 */
public abstract class AbstractBaseTest {
    private SqlSession session;

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

    /**
     * get SqlSession.
     *
     * @return SqlSession
     */
    protected SqlSession getSession() {
        return session;
    }
}
