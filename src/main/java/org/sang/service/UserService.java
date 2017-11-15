package org.sang.service;

import org.sang.mapper.UserMapper;
import org.sang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;

    public User loginUser(String username, String password) {
        User user = userMapper.getUser(username, password);
        return user;
    }
    //前台注册保存用户
	public void saveUser(User user) {
		user.setRole(1);
		userMapper.saveUser(user);
	}
	public void updateUser(User user){
		user.setRole(1);
		userMapper.updateByPrimaryKeyDemo(user);
	}
	//前台判断邮箱是否重复
	public boolean checkUsername(String username) {
        User user = userMapper.getUsername(username);
        if (user == null) {
            return true;
        }else{
            return false;
        }
    }
}
