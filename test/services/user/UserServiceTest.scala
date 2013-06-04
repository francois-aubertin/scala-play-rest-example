package services.user

import org.mockito.Mockito._
import org.junit.Test
import repositories.user.UserRepositoryComponent
import domain.user.User

class UserServiceTest extends UserServiceComponentImpl
                      with UserRepositoryMockComponent {
    
    @Test
    def createUser() {
        val user = User(Option(1L),
                        "test@test.com")
        
        userService.createUser(user)
        
        verify(userRepository).createUser(user)
    }

}

trait UserRepositoryMockComponent extends UserRepositoryComponent {
    
    override val userRepository = mock(classOf[UserRepository])
    
}
