package services.user

import domain.user.User
import repositories.user.UserRepositoryComponent

trait UserServiceComponent {
    
    val userService: UserService
    
    trait UserService {
        def saveUser(user: User): User
    }

}

trait UserServiceComponentImpl extends UserServiceComponent {
    self: UserRepositoryComponent =>
    
    override val userService = new UserServiceImpl
    
    class UserServiceImpl extends UserService {
        
        override def saveUser(user: User): User = {
            userRepository.saveUser(user)
        }
    }
}
