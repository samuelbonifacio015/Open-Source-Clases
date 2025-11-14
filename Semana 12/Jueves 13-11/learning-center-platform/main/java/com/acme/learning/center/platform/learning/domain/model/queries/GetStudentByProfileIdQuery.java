package com.acme.learning.center.platform.learning.domain.model.queries;

import com.acme.learning.center.platform.learning.domain.model.valueobjects.ProfileId;

/**
 * Query to get a student by their profile ID.
 */
public record GetStudentByProfileIdQuery(ProfileId profileId) {

  public GetStudentByProfileIdQuery {
    if (profileId == null || profileId.profileId() == null ||
        profileId.profileId() <= 0) {
      throw new IllegalArgumentException("ProfileId cannot be null " +
          "or less than or equal to zero");
    }
  }
}
