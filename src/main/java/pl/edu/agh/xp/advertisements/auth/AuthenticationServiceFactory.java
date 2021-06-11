package pl.edu.agh.xp.advertisements.auth;

import pl.edu.agh.xp.advertisements.configuration.AdvertisementConfiguration;
import pl.edu.agh.xp.advertisements.service.csv.FileName;

public class AuthenticationServiceFactory {

    public static AuthenticationService create() {
        // only one implementation so far
        return new UsersFileAuthenticationService(FileName.create(AdvertisementConfiguration.INSTANCE.pathToUsers));
    }

}
