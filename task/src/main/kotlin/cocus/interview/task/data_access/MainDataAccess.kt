package cocus.interview.task.data_access

import cocus.interview.task.GitHubServerException
import cocus.interview.task.UnknownUsernameException
import cocus.interview.task.structures.GitHubBranch
import cocus.interview.task.structures.GitHubRepository
import com.google.gson.Gson
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*
import kotlin.collections.ArrayList

/**
 * Class responsible for data access. Whether it's from an API or a Database, this is where the data requests happen
 */

@Component
class MainDataAccess {

    private val API_URL: String = "https://api.github.com"

    fun getAllUserRepositories(username: String) : LinkedList<GitHubRepository>? {
        val response = buildRequest(API_URL, "users/$username/repos")

        if (response == null) throw GitHubServerException("Error from the API")
        //return ErrorResponse(500, "Error from the API")
        if (response.statusCode() == 404) throw UnknownUsernameException("Username does not exist")
        //(ErrorResponse(response.statusCode(), "Username does not exist")

        val repositoryListString = response.body()

        val gson = Gson()
        val repositoryList = gson.fromJson(repositoryListString, Array<GitHubRepository>::class.java)
        val repositoryLinkedList = LinkedList(repositoryList.asList())

        //Data Visualization
        for (repo: GitHubRepository in repositoryList)
            println("${repo.name} -> ${repo.owner}")

        return repositoryLinkedList

    }

    fun getAllRepositoryBranches(username: String, repositoryName: String) : Array<GitHubBranch>? {
        val response = buildRequest(API_URL, "repos/$username/$repositoryName/branches")
        val branchListString = response?.body()

        val gson = Gson()
        val branchList = gson.fromJson(branchListString, Array<GitHubBranch>::class.java)

        for (branch: GitHubBranch in branchList) {
            println("${branch.name} -> ${branch.commit}")
        }

        return branchList
    }
}

private val ACCESS_TOKEN = "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"

fun buildRequest(url: String, path: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()

    println("$url/$path")

    val request = HttpRequest.newBuilder()
        .uri(URI.create("$url/$path"))
        .GET()
        .setHeader("Authorization", "Bearer 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")
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    return response
}