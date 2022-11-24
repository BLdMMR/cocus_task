package cocus.interview.task.services

import cocus.interview.task.data_access.MainDataAccess
import cocus.interview.task.structures.GitHubRepository
import org.springframework.stereotype.Component

/**
 * Class responsible for treating and validating the data that is passed and returned
 */
@Component
class MainServices (private val repository: MainDataAccess) {

    fun getAllUserRepositories(username: String) : Array<GitHubRepository>? {
        if (username.trim() == "") return null
        return repository.getAllUserRepositories(username)
        //return "from services ${repository.getAllUserRepos(username)}"
    }
}