package cocus.interview.task.controllers

import cocus.interview.task.responses.ErrorResponse
import cocus.interview.task.responses.ResponseMessage
import cocus.interview.task.services.MainServices
import cocus.interview.task.structures.GitHubBranch
import cocus.interview.task.structures.GitHubRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Class responsible for recieving HTTP requests and returning responses
 */

@RestController
@RequestMapping("/")
class MainController (private val services: MainServices) {

    @GetMapping("/{username}")
    fun getAllUserRepositories(@PathVariable username: String) : ResponseEntity<ResponseMessage>? {
        val repositoryList = services.getAllUserRepositories(username)

        if (repositoryList != null) {
            return ResponseEntity<ResponseMessage>(repositoryList, HttpStatus.valueOf(repositoryList.status))
        }
        else return null
    }

    @GetMapping("/{username}/{repositoryName}")
    fun getBranches(@PathVariable username: String, @PathVariable repositoryName: String) : Array<GitHubBranch>? {
        return services.getAllRepositoryBranches(username, repositoryName)
    }

//    @GetMapping("/test-api")
//    fun getTestApiConnection(): String? {
//        return buildRequest("", "")
//    }
}