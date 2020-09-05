package cn.denvie.mybatis.plus;

import cn.denvie.mybatis.plus.model.User;
import cn.denvie.mybatis.plus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
class MybatisPlusApplicationTests {
    private final UserService userService;

    @Test
    public void testSave() {
        // 创建用户
        User newUser = new User();
        newUser.setUsername("denvie100");
        newUser.setPassword("pwd12345");
        newUser.setNickname("尛飛俠100");
        newUser.setAge(18);
        newUser.setEmail("7382763@qq.com");
        newUser.setDescription("闭家锁100");
        userService.save(newUser);
        System.out.println(newUser);
    }

    @Test
    public void testListAll() {
        // 查询所有用户
        List<User> users = userService.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testPage() {
        Page<User> page = new Page<>(1, 5);
        Page<User> userPage = userService.page(page);
        System.out.println("total: " + userPage.getTotal());
        for (User user : userPage.getRecords()) {
            System.out.println(user);
        }
    }

    @Test
    public void testDelete() {
        userService.removeByIds(Arrays.asList(2, 3, 4));
        testPage();
    }

    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("nickname")
                .between("age", 20, 40)
                .orderByDesc("id");
        userService.list(wrapper).forEach(System.out::println);
    }
}
