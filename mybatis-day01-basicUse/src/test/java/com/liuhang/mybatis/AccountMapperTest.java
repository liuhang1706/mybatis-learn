package com.liuhang.mybatis;

import com.liuhang.mybatis.dao.AccountMapper;
import com.liuhang.mybatis.entity.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class AccountMapperTest {

    private InputStream is;
    private SqlSession sqlSession;
    private AccountMapper mapper;

    /**
     * 测试之前执行，用于初始化
     */
    @Before
    public void init() throws Exception {
        // 1. 读取配置文件
        is = Resources.getResourceAsStream("mybatis-config.xml");
        // 2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        // 3. 获取SqlSession对象
        sqlSession = factory.openSession();
        // 4. 使用SqlSession创建Mapper的代理对象
        mapper = sqlSession.getMapper(AccountMapper.class);
    }

    /**
     * 测试结束执行，用于提交事务和释放资源
     */
    @After
    public void destroy() throws Exception {
        // 6. 提交事务
        sqlSession.commit();
        // 7. 释放资源
        sqlSession.close();
        is.close();
    }

    /**
     * getAccountByUid
     */
    @Test
    public void getAccountByUid() {
        // 5. 使用代理对象执行查询
        List<Account> accounts = mapper.getAccountByUid(46);
        accounts.forEach(System.out::println);
    }

    /**
     * 测试查询所有账户
     */
    @Test
    public void testListAllAccounts() {
        // 5. 使用代理对象执行查询
        List<Account> accounts = mapper.listAllAccounts();
        accounts.forEach(System.out::println);
    }

    /**
     * 查询所有账户，带有用户信息
     * 一对一 ，延时加载用户信息
     * @return
     */
    @Test
    public void listAllAccountsWithLazyUser() {
        // 5. 使用代理对象执行查询
        List<Account> accounts = mapper.listAllAccountsWithLazyUser();
        // 由于打印user信息，将导致每一条account去执行查询对应user的操作
        // 如果注释掉打印语句，将只会查询一次account信息
        accounts.forEach(System.out::println);
    }
}
