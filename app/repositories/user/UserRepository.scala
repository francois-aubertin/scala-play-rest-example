package repositories.user

import domain.user.User
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

trait UserRepositoryComponent {
    val userRepository: UserRepository
    
    trait UserRepository {
        
        def createUser(user: User): User
        
        def updateUser(user: User)
        
    }
}

trait UserRepositoryComponentImpl extends UserRepositoryComponent {
    override val userRepository = new UserRepositoryImpl
    
    class UserRepositoryImpl extends UserRepository {
        
        val users = new ConcurrentHashMap[Long, User]
        val idSequence = new AtomicLong(0)
        
        override def createUser(user: User): User = {
            val newId = idSequence.incrementAndGet()
            val createdUser = user.copy(id = Option(newId))
            users.put(newId, createdUser)
            createdUser
        }
        
        override def updateUser(user: User) {
            users.put(user.id.get, user)
        }
        
    }
    
}