package com.vrm.serviceimpl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vrm.dto.UserDto;
import com.vrm.entity.User;
import com.vrm.exception.UserNotFoundException;
import com.vrm.repository.UserRepository;
import com.vrm.service.IUserService;


@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	static Logger logger=LoggerFactory.getLogger(UserService.class.getClass());

	public String validateUser(int userId ,String Password)
	{
	  if( userRepository.findByUserIdAndPassword(userId, Password) != null) {
		  return "Valid User";
	  }
      return "Invalid user,Please Register as a Customer or Driver";
	}
	
	public List<UserDto> removeUser(int id) throws UserNotFoundException
	{
		userRepository.deleteById(id);
		if(userRepository.findAll()==null) {
			throw new UserNotFoundException();
		}
		else {
			List<User> userList = userRepository.findAll();
			List<UserDto> userDtoList = new ArrayList<>();
			UserDto userDto = new UserDto();
			for(User user: userList) {
				BeanUtils.copyProperties(user, userDto);
				userDtoList.add(userDto);
			}
			logger.info("Removing User");
			return userDtoList;
		}
	}
	
	
	public String SignOut(String message) 
	{
		
	   if(message.equals("SignOut")) {
		   return "signed out succesfully";
	   }
	   return "Still Signed In";
	}

}

