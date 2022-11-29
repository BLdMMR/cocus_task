package cocus.interview.task.controllers

import cocus.interview.task.GitHubServerException
import cocus.interview.task.NoReadableCharactersException
import cocus.interview.task.UnknownErrorException
import cocus.interview.task.UnknownUsernameException
import cocus.interview.task.services.MainServices
import cocus.interview.task.structures.*
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Class responsible for recieving HTTP requests and returning responses
 */

@RestController
@RequestMapping("/")
class MainController (private val services: MainServices) {

    @GetMapping("/{username}")
    fun getAllUserRepositories(@PathVariable username: String, @RequestHeader(HttpHeaders.ACCEPT) accept: String) : ResponseEntity<OutputEntity>? {
        return try {
            val repositoryList = services.getAllUserRepositories(username)
            val ret = GitHubRepositoryList(repositoryList!!)
            println("\n\n$ret")
            return ResponseEntity.ok().body(GitHubRepositoryList(repositoryList))

        } catch (gse: GitHubServerException) {
            ResponseEntity(ErrorResponse(500, gse.message!!), HttpStatus.INTERNAL_SERVER_ERROR)

        } catch (uue: UnknownUsernameException) {
            ResponseEntity(ErrorResponse(404, uue.message!!), HttpStatus.NOT_FOUND)

        } catch (error: UnknownErrorException) {
            ResponseEntity(ErrorResponse(400, error.message!!), HttpStatus.BAD_REQUEST)

        } catch (nrce: NoReadableCharactersException) {
            ResponseEntity(ErrorResponse(400, nrce.message!!), HttpStatus.BAD_REQUEST)
        }
    }

}