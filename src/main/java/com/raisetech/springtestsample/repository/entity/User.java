package com.raisetech.springtestsample.repository.entity;

import java.util.Objects;

public class User {
  private int id;
  private String name;

  public User(String name) {
    this.id = 0; // デフォルト値
    this.name = name;
  }

  public User(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    if (this.id == 0) {
      throw new IllegalStateException("user id was 0. it is before insertion");
    }
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
