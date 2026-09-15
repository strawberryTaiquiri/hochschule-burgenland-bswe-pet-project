package io.muehlbachler.bswe.service.impl;

import java.util.ArrayList;
import java.util.List;

import io.muehlbachler.bswe.error.ApiException;
import io.muehlbachler.bswe.model.User;
import io.muehlbachler.bswe.repository.UserRepository;
import io.muehlbachler.bswe.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {
  private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private final UserRepository userRepository;

  @Override
  public boolean exists(final String userId) {
    if(userId == null || "".equals(userId))
      return false;
    return userRepository.existsById(userId);
  }

  @Override
  public List<User> list() {
    final List<User> result = new ArrayList<>();
    Iterable<User> all = userRepository.findAll();

    for(User i: all) {
      result.add(i);
    }

    return result;
  }

  @Override
  public User save(final User user) throws ApiException {
    return userRepository.save(user);
  }

  @Override
  public boolean delete(final String userId) {
    if (exists(userId)) {
      userRepository.deleteById(userId);
      return true;
    }
    return false;
  }
}
