package repositories.user

import domain.user.User
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait UserRepositoryComponent {
    val userRepository: UserRepository
    
    trait UserRepository {
        def saveUser(user: User): User
    }
}

trait UserRepositoryComponentImpl extends UserRepositoryComponent {
    override val userRepository = new UserRepositoryImpl
    
    class UserRepositoryImpl extends UserRepository {
        
        val users = new ConcurrentHashMap
        val idSequence = new AtomicLong(0)
        
        override def saveUser(user: User): User = {
            val id = user.id
            null
        }
        
    }
    
}