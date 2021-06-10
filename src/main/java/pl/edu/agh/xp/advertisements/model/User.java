package pl.edu.agh.xp.advertisements.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {

    String username;
    String password;
    UserType userType;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
    }

}
