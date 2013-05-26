package repositories.user

import org.junit.Test
import repositories.user.{UserRepositoryComponentImpl => UserRepositoryImpl}

class UserRepositoryTest {

    private val userRepository = new UserRepositoryImpl {}
    
    @Test
    def crud() {
        println("Test HEY HEY HEY")
    }
    
}