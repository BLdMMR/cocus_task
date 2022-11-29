package cocus.interview.task.services

import cocus.interview.task.GitHubServerException
import cocus.interview.task.NoReadableCharactersException
import cocus.interview.task.UnknownErrorException
import cocus.interview.task.UnknownUsernameException
import cocus.interview.task.data_access.MainDataAccess
import cocus.interview.task.structures.GitHubBranch
import cocus.interview.task.structures.GitHubRepository
import org.springframework.stereotype.Component
import java.util.*

/**
 * Class responsible for treating and validating the data that is passed and returned
 */
@Component
class MainServices (private val repository: MainDataAccess) {

    fun getAllUserRepositories(username: String) : LinkedList<GitHubRepository>? {
        if (username.trim() == "") throw NoReadableCharactersException("Username has no readable characters")
        try {
            val repositoryList = repository.getAllUserRepositories(username) ?: throw UnknownErrorException("Unknown Error")

            val finalList = LinkedList<GitHubRepository>()
            for (repo: GitHubRepository in repositoryList) {
                if (!repo.fork) {
                    repo.branches = repository.getAllRepositoryBranches(username, repo.name)
                    finalList.add(repo)
                }
            }
            return finalList

        } catch (uue : UnknownUsernameException) {
            throw uue
        } catch (gse : GitHubServerException) {
            throw gse
        }


    }

}