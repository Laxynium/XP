package pl.edu.agh.xp.advertisements.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoggedInUser {

    String username;
    UserType userType;

}
