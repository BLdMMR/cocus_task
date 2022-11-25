package cocus.interview.task.services

import cocus.interview.task.data_access.MainDataAccess
import cocus.interview.task.responses.ErrorResponse
import cocus.interview.task.responses.ResponseMessage
import cocus.interview.task.responses.SuccessResponse
import cocus.interview.task.structures.GitHubBranch
import cocus.interview.task.structures.GitHubRepository
import org.springframework.stereotype.Component

/**
 * Class responsible for treating and validating the data that is passed and returned
 */
@Component
class MainServices (private val repository: MainDataAccess) {

    fun getAllUserRepositories(username: String) : ResponseMessage? {
        if (username.trim() == "") return ErrorResponse("400", "Username has no readable characters")
        val repositoryList = repository.getAllUserRepositories(username)
        if (repositoryList.isNullOrEmpty()) return ErrorResponse("404", "Username not found or ")
        for (repo: GitHubRepository in repositoryList) {
            repo.branches = repository.getAllRepositoryBranches(username, repo.name)
        }

        return SuccessResponse("200", repositoryList)

        //return "from services ${repository.getAllUserRepos(username)}"
    }

    //Test Function
    fun getAllRepositoryBranches(username: String, repositoryName: String): Array<GitHubBranch>? {
        return repository.getAllRepositoryBranches(username, repositoryName)
    }
}