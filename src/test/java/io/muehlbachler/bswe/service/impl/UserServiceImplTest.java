package io.muehlbachler.bswe.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import io.muehlbachler.bswe.model.User;
import io.muehlbachler.bswe.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
  @Mock
  private UserRepository userRepository;

  private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(userRepository);
  }

  @Test
  public void testExistsWithValidUserId() {
    when(userRepository.existsById("user1")).thenReturn(true);

    assertTrue(userService.exists("user1"));
    verify(userRepository).existsById("user1");
  }

  @Test
  public void testExistsWithInvalidUserId() {
    assertFalse(userService.exists(null));
    assertFalse(userService.exists(""));
    verify(userRepository, never()).existsById(any());
  }

  @Test
  public void testExistsWithNonExistentUserId() {
    when(userRepository.existsById("nonexistent")).thenReturn(false);
    assertFalse(userService.exists("nonexistent"));
    verify(userRepository).existsById("nonexistent");
  }

  @Test
  public void testList() {
    User user1 = new User("user1", "User One");
    User user2 = new User("user2", "User Two");
    List<User> expectedUsers = Arrays.asList(user1, user2);
    when(userRepository.findAll()).thenReturn(expectedUsers);

    List<User> actualUsers = userService.list();

    assertEquals(expectedUsers, actualUsers);
    verify(userRepository).findAll();
  }

  // TODO: add more tests
}
