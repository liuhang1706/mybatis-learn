package com.liuhang.mybatis;

import com.liuhang.mybatis.dao.UserMapper;
import com.liuhang.mybatis.entity.QueryVo;
import com.liuhang.mybatis.entity.User;
import com.liuhang.mybatis.entity.User2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {

    private InputStream is;
    private SqlSession sqlSession;
    private UserMapper mapper;

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
        mapper = sqlSession.getMapper(UserMapper.class);
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
     * 测试添加用户
     */
    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("dddd");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setSex("男");
        user.setAddress("广东");

        // 调用mapper完成添加
        int count = mapper.saveUser(user);
        System.out.println("添加条数为 : " + count);
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testremoveUserById(){
        // 这里传的id为自己数据库中存在的id值
        int count = mapper.removeUserById(50);
        System.out.println("删除条数为 ： " + count);
    }

    /**
     * 测试修改用户
     */
    @Test
    public void testUpdateUser() {
        // 因为还没有编写根据id查询用户，所以模拟数据
        User user = new User();
        user.setUsername("update");
        user.setAddress("test");
        user.setSex("女");
        user.setBirthday(new Date(System.currentTimeMillis()));
        // id 为自己数据库中存在的值
        user.setId(49);

        // 执行修改
        int count = mapper.updateUser(user);
        System.out.println("修改条数为 ： " + count);
    }

    /**
     * 测试查询单个用户
     */
    @Test
    public void testGetUserById() {
        // 确保id存在，否则返回null
        User user = mapper.getUserById(48);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testListUsersByName() {
        List<User> users = mapper.listUsersByName("%王%");
        // 使用 Stream 流 + 方法引用，需要至少jdk8
        users.forEach(System.out::println);
    }

    /**
     * 测试查询用户总数
     */
    @Test
    public void testCountUser() {
        int count = mapper.countUser();
        System.out.println("用户总记录数为 ： " + count);
    }


    /**
     * 测试添加用户，并获取 id 的返回值
     */
    @Test
    public void testSaveUser2() {
        User user = new User();
        user.setUsername("鱼开饭");
        user.setBirthday(new Date(System.currentTimeMillis()));
        user.setSex("男");
        user.setAddress("广东");

        System.out.println("添加前 ： " + user);

        // 调用mapper完成添加
        int count = mapper.testSaveUser2(user);
        System.out.println("添加条数为 : " + count);

        System.out.println("添加后 ： " + user);
    }

    /**
     * 测试根据Vo查询
     */
    @Test
    public void testListUsersByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("王");
        vo.setUser(user);

        List<User> users = mapper.listUsersByVo(vo);
        users.forEach(System.out::println);
    }

    /**
     * 测试根据查询，使用 resultMap
     */
    @Test
    public void testListUsers(){
        List<User2> users = mapper.listAllUsers2();
        users.forEach(System.out::println);
    }

    /**
     * 测试多条件查询，动态SQL
     * 标签
     * <where>
     *     <if></if>
     * </where>
     */
    @Test
    public void testListUsersByCondition() {
        User user = new User();
        // 加入查询条件
        user.setUsername("王");
        user.setSex("男");
        // 查询并展示
        List<User> users = mapper.listUsersByCondition(user);
        users.forEach(System.out::println);
    }

    /**
     * 测试根据id集合查询，动态SQL
     * 标签：
     * <foreach>
     *
     * </foreach>
     */
    @Test
    public void testListUsersByIds(){
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(41);
        ids.add(42);
        ids.add(46);
        vo.setIds(ids);

        List<User> users = mapper.listUsersByIds(vo);
        users.forEach(System.out::println);
    }

    /**
     * 测试根据查询，
     * 一对多
     */
    @Test
    public void listAllUsersWithAccounts(){
        List<User> users = mapper.listAllUsersWithAccounts();
        users.forEach(System.out::println);
    }

    /**
     * 测试查询带有角色的用户信息
     * 多对多
     */
    @Test
    public void testListUsersWithRoles(){
        List<User> users = mapper.listUsersWithRoles();
        users.forEach(System.out::println);
    }


    /**
     * 测试查询用户信息包含账户信息
     * 一对多 : 延时加载账户信息
     */
    @Test
    public void listAllUsersWithLazyAccounts(){
        List<User> users = mapper.listAllUsersWithLazyAccounts();
        // 由于打印user信息，将导致每一条user去执行查询对应account的操作
        // 如果注释掉打印语句，将只会查询一次user信息
        users.forEach(System.out::println);
    }

    /**
     * Mybatis 入门案例
     */
    @Test
    public void testInit() {
        try {
            // 1. 读取配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            // 2. 创建 SqlSessionFactory 工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
            // 3. 获取 SqlSession 对象
            SqlSession sqlSession = factory.openSession();
            // 4. 使用 SqlSession 创建 Mapper 的代理对象
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 5. 使用代理对象执行查询
            List<User> users = mapper.listAllUsers();
            users.forEach(System.out::println);
            // 6. 释放资源
            sqlSession.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
