package com.feedle.feedleapi.Services;

import com.feedle.feedleapi.Models.*;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void registerUser(User user);

    User authorizeUser(String username, String password);

    void deleteUser(int id);

    List<User> getUser();

    User updateUser(User user);

    UserInformation getUserInformationById(int id);

    UserConversation sendMessage(Message message);

    List<UserConversation> getUserConversationsByUserId (int id);

    Boolean checkIfTheLastMessageIdIsEqualsToGivenId(int userId, int givenId);

}
