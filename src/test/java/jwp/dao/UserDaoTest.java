package jwp.dao;

import jwp.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
//    private UserDao userDao = new UserDao();
//    @BeforeEach
//    void init(){
//        List<User> users = userDao.findAll();
//        for(User user: users){
//            userDao.delete(user);
//        }
//        userDao = new UserDao();
//        User user = new User("1","1234","박서연","ksp509@konkuk.ac.kr");
//        userDao.insert(user);
//    }
//
//    @Test
//    void insert() {
//        User user = new User("2","1234","박서연","ksp509@konkuk.ac.kr");
//        userDao.insert(user);
//
//        assertThat(userDao.findByUserId("2").getName()).isEqualTo("박서연");
//    }
//
//    @Test
//    void update() {
//        User user = new User("1","1234","밥서연","ksp509@konkuk.ac.kr");
//        userDao.update(user);
//
//        assertThat(userDao.findByUserId("1").getName()).isEqualTo("밥서연");
//    }
}