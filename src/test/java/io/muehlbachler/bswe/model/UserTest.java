package io.muehlbachler.bswe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.muehlbachler.bswe.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


class UserTest {

  @Mock
  private UserRepository userRepository;


  @Test
  void testDefaultConstructor() {
    User user = new User();
    assertNull(user.getId());
    assertNull(user.getUsername());
  }

  @Test
  void testParameterizedConstructor() {
    String id = "test-id";
    String username = "testuser";

    User user = new User(id, username);

    assertEquals(id, user.getId());
    assertEquals(username, user.getUsername());
  }

  @Test
  void testWithId() {
    String id = "test-id";
    User user = User.withId(id);

    assertEquals(id, user.getId());
    assertNull(user.getUsername());
  }

  @Test
  void testEqualsAndHashCode() {
    User user1 = new User("id1", "user1");
    User user2 = new User("id1", "user2");
    User user3 = new User("id2", "user1");

    assertEquals(user1, user2);
    assertNotEquals(user1, user3);
    assertEquals(user1.hashCode(), user2.hashCode());
    assertNotEquals(user1.hashCode(), user3.hashCode());
  }

  @Test
  void testToString() {
    User user = new User("test-id", "testuser");
    String toString = user.toString();

    assertTrue(toString.contains("test-id"));
    assertTrue(toString.contains("testuser"));
  }
}
