package com.parking.prakinglot.ejb;

import com.parking.prakinglot.common.UserDto;
import com.parking.prakinglot.entities.User;
import com.parking.prakinglot.entities.UserGroup;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Pbkdf2PasswordHash passwordHash;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");

        try {
            TypedQuery<User> typedQuery = entityManager.createQuery(
                    "SELECT u FROM User u", User.class
            );
            List<User> users = typedQuery.getResultList();

            return copyUsersToDto(users);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail()
            );
            userDtos.add(userDto);
        }

        return userDtos;
    }

    public void createUser(String username, String email, String password, Collection<String> userGroups) {
        LOG.info("createUser: " + username);

        try {
            // Create new User entity
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);

            // Hash the password before storing
            user.setPassword(passwordHash.generate(password.toCharArray()));

            // Persist the user
            entityManager.persist(user);

            // Assign user groups to the user
            assignGroupsToUser(username, userGroups);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private void assignGroupsToUser(String username, Collection<String> userGroups) {
        LOG.info("assignGroupsToUser: " + username + " - Groups: " + userGroups);

        try {
            // Iterate through the collection of user groups
            for (String groupName : userGroups) {
                // Create new UserGroup entity for each group
                UserGroup userGroup = new UserGroup();
                userGroup.setUsername(username);
                userGroup.setUserGroup(groupName);

                // Persist each UserGroup
                entityManager.persist(userGroup);
            }

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    public Collection<String> findUsernamesByUserIds(Collection<Long> userIds) {
        List<String> usernames = entityManager.createQuery("SELECT u.username FROM User u WHERE u.id IN :userIds", String.class)
                .setParameter("userIds", userIds).getResultList();
        return usernames;
    }
}