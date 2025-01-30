package com.eazyapp.service.Implementation;

import com.eazyapp.dto.RoleDTO;
import com.eazyapp.dto.UserDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.model.Role;
import com.eazyapp.model.User;
import com.eazyapp.model.UserLoggedIn;
import com.eazyapp.repository.RoleRepository;
import com.eazyapp.repository.UserLoggedRepository;
import com.eazyapp.repository.UserRepository;
import com.eazyapp.requestwrapper.UserLoginRequestWrapper;
import com.eazyapp.requestwrapper.UserRequestWrapper;
import com.eazyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserLoggedRepository userLoggedRepository;

    public void createUser(UserRequestWrapper userRequestWrapper) throws EazyShoppyException{
        Optional<User> user= userRepository.findUserFromEmail(userRequestWrapper.getEmail());
        Optional<Role> role =roleRepository.findById(userRequestWrapper.getRoleId());
        if(user.isEmpty())
        {
            User user1= new User();
            user1.setName(userRequestWrapper.getName());
            user1.setEmail(userRequestWrapper.getEmail());

            String randomUUID = UUID.randomUUID().toString();
            while (null != userRepository.findOneUniqueId(randomUUID)) {
                randomUUID = UUID.randomUUID().toString();
            }

            user1.setUniqueId(randomUUID);
            user1.setPassword(userRequestWrapper.getPassword());
            user1.setPhoneNumber(userRequestWrapper.getPhoneNumber());

            if (role.isPresent())
            {
                user1.setRole(role.get());
            }else {
                throw  new EazyShoppyException("Role already exist",400);
            }
            userRepository.save(user1);
        }
        else {
            throw  new EazyShoppyException("User already exist",400);
        }
    }


    public long login(UserLoginRequestWrapper userLoginRequestWrapper) throws EazyShoppyException
    {
        Optional<User> user= userRepository.findUserFromEmail(userLoginRequestWrapper.getEmail());

        userLoggedRepository.deleteAll();

        if (user.isPresent())
        {
            if (user.get().getPassword().equals(userLoginRequestWrapper.getPassword())) {
                UserLoggedIn newUserLogged= new UserLoggedIn();

                newUserLogged.setUser(user.get());
                userLoggedRepository.save(newUserLogged);
                return user.get().getId();
            }
        }

        return 0;
    }
    public UserDTO getUserById(long id) throws EazyShoppyException
    {
        Optional<User> user= userRepository.findById(id);
        if(user.isPresent())
        {
        UserDTO userDTO= new UserDTO();
        userDTO.setName(user.get().getName());
        userDTO.setEmail(user.get().getEmail());
       userDTO.setUniqueId(user.get().getUniqueId());
        userDTO.setPhoneNumber(user.get().getPhoneNumber());
        userDTO.setRoleType(user.get().getRole().getRoleType());
        return userDTO;
        }
        else {
            throw  new EazyShoppyException("user for this id  not exist",400);
        }
    }
    public List<UserDTO> getAllUser() throws EazyShoppyException
    {
        List<User> users= userRepository.findAll();
        List<UserDTO> userDTOS=new ArrayList<>();
        for (User user: users)
        {
            UserDTO userDTO= new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setUniqueId(user.getUniqueId());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setRoleType(user.getRole().getRoleType());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
