package repositories.user

import org.junit.Test
import org.junit.Assert._
import domain.user.User

class UserRepositoryTest extends UserRepositoryComponentImpl {
    
    @Test
    def crud() {
        // Creation.
        val user = User(Option.empty, "test@abc.com")
        val createdUser = userRepository.createUser(user)
        
        assertTrue(createdUser.id.isDefined)
        
        // Retrieval.
    }
    
}