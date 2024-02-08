package prepared_statement;

import entity.User;

import java.util.List;

public class Test {
    private static UserRepository userRepository;
    public static void main(String[] args) {
        userRepository = UserRepository.getInstance();
        List<User> userList = userRepository.findAll();
        System.out.println(userList.size());
    }
}
