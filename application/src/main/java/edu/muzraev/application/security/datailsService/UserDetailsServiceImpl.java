package edu.muzraev.application.security.datailsService;


import edu.muzraev.application.dao.UserDao;
import edu.muzraev.application.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDAO(UserDao userDao) {
        this.userDao = userDao;
    }



    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User>optionalUser = userDao.getUserByName(s);
        if (optionalUser.isEmpty()){
            return (UserDetails) Optional.empty().get();
        }
        return optionalUser.get();
    }



}
