package storm.server.gateway.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import storm.server.gateway.model.UserDTO;
import storm.server.gateway.model.Users;
import storm.server.gateway.repository.UsersRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    public UserDTO register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        String sqlInsertAuthorities = "INSERT INTO authorities VALUES (?, ?)";
        jdbcTemplate.update(sqlInsertAuthorities, userDTO.getUsername(), "ROLE_USER");
        return UserDTO.entityToDTO(usersRepository.save(UserDTO.dtoToEntity(userDTO)));
    }
}
