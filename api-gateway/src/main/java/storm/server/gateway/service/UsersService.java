package storm.server.gateway.service;

import storm.server.gateway.model.UserDTO;
import storm.server.gateway.model.Users;

import java.util.List;

public interface UsersService {

    public List<Users> getAllUsers();

    public UserDTO register(UserDTO userDTO);
}
