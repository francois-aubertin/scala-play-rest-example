package services.user

import domain.user.User
import repositories.user.UserRepositoryComponent

trait UserServiceComponent {
    
    val userService: UserService
    
    trait UserService {
        
        def createUser(user: User): User
        
        def updateUser(user: User)
        
    }

}

trait UserServiceComponentImpl extends UserServiceComponent {
    self: UserRepositoryComponent =>
    
    override val userService = new UserServiceImpl
    
    class UserServiceImpl extends UserService {
        
        override def createUser(user: User): User = {
            userRepository.createUser(user)
        }
        
        override def updateUser(user: User) {
            userRepository.updateUser(user)
        }
        
    }
}
