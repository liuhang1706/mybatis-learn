package com.liuhang.mybatis.dao;

import com.liuhang.mybatis.entity.QueryVo;
import com.liuhang.mybatis.entity.User;
import com.liuhang.mybatis.entity.User2;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     */
    List<User> listAllUsers();

    /**
     * 添加用户
     * @param user
     * @return 成功返回1，失败返回0
     */
    int saveUser(User user);

    /**
     * 测试添加用户，并获取 id 的返回值
     * @param user
     * @return 成功返回1，失败返回0
     */
    int testSaveUser2(User user);

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return 成功返回1，失败返回0
     */
    int removeUserById(Integer userId);

    /**
     * 修改用户
     * @param user
     * @return 成功返回1，失败返回0
     */
    int updateUser(User user);

    /**
     * 根据id查询单个用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据姓名模糊查询多个用户
     *
     * @param username
     * @return
     */
    List<User> listUsersByName(String username);

    /**
     * 查询用户总数
     *
     * @return
     */
    int countUser();

    /**
     * 根据查询条件模糊查询用户
     *
     * @param vo
     * @return
     */
    List<User> listUsersByVo(QueryVo vo);

    /**
     * 查询所有用户，使用 resultMap 来映射数据库字段和实体类的变量
     */
    List<User2> listAllUsers2();

    /**
     * 根据传入的查询条件，进行多条件查询
     * 动态SQL：
     * <where>
     *     <if></if>
     * </where>
     *
     * @param user 包含查询的条件，可能包含 username、sex、birthday、address等条件
     * @return
     */
    List<User> listUsersByCondition(User user);

    /**
     * 根据id集合，查询用户
     *
     *
     * @param vo
     * @return
     */
    List<User> listUsersByIds(QueryVo vo);

    /**
     * 配置查询所有用户，并且带有账户信息
     * 一对多
     */
    List<User> listAllUsersWithAccounts();

    /**
     * 查询所有用户信息，包含用户所拥有的角色信息
     * 多对多
     */
    List<User> listUsersWithRoles();

    /**
     * 查询所有用户信息，包含用户所拥有的账户信息
     * 多对多：延时加载账户信息
     */
    List<User> listAllUsersWithLazyAccounts();
}
