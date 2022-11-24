package cocus.interview.task.controllers

import cocus.interview.task.data_access.buildRequest
import cocus.interview.task.services.MainServices
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
    fun getRepos(@PathVariable username: String) : String? {
        return services.getAllUserRepositories(username)
    }

//    @GetMapping("/test-api")
//    fun getTestApiConnection(): String? {
//        return buildRequest("", "")
//    }
}