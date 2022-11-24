package cocus.interview.task.services

import cocus.interview.task.data_access.MainDataAccess
import org.springframework.stereotype.Component

/**
 * Class responsible for treating and validating the data that is passed and returned
 */
@Component
class MainServices (private val repository: MainDataAccess) {

    fun getAllUserRepositories(username: String) : String? {
        if (username.trim() == "") return "No readable characters"
        return repository.getAllUserRepos(username)
        //return "from services ${repository.getAllUserRepos(username)}"
    }
}