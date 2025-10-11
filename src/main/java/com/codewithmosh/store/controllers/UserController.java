package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserController(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @GetMapping
  public Iterable<UserDto> getAllUsers(
//      @RequestHeader( required = false, name = "x-auth-token") String authToken,
      @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
//    System.out.println("Auth Token: " + authToken); // For demonstration purposes only

    if (!Set.of("name", "email").contains(sortBy)) sortBy = "name"; // validate sort parameter

    return userRepository.findAll(Sort.by(sortBy)).stream().map(userMapper::toDto).toList();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    var user = userRepository.findById(id).orElse(null);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(userMapper.toDto(user));
  }

  @PostMapping
  public ResponseEntity createUser(
      @RequestBody RegisterUserRequest request,
      UriComponentsBuilder uriBuilder) {
    var user = userMapper.toEntity(request);
    userRepository.save(user);

    var userDto = userMapper.toDto(user);
    var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(uri).body(userDto);
  }
}
