package com.citizen.platform.u202317269.citizen.domain.model.queries;

/**
 * Query to get a citizen by nickName.
 * @author Samuel Bonifacio
 */
public record GetCitizenByNickNameQuery(String nickName) {
  public GetCitizenByNickNameQuery {
    if (nickName == null || nickName.isBlank()) {
      throw new IllegalArgumentException("NickName cannot be null or empty");
    }
  }
}
