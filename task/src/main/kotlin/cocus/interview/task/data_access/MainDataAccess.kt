package cocus.interview.task.data_access

import cocus.interview.task.responses.ErrorResponse
import cocus.interview.task.responses.ResponseMessage
import cocus.interview.task.responses.SuccessResponse
import cocus.interview.task.structures.GitHubBranch
import cocus.interview.task.structures.GitHubRepository
import com.google.gson.Gson
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * Class responsible for data access. Whether it's from an API or a Database, this is where the data requests happen
 */

@Component
class MainDataAccess {

    private val API_URL: String = "https://api.github.com"

    fun getAllUserRepositories(username: String) : ResponseMessage {
        val response = buildRequest(API_URL, "users/$username/repos")

        if (response == null) return ErrorResponse(500, "Error from the API")
        if (response.statusCode() == 404) return ErrorResponse(response.statusCode(), "Username does not exist")

        val repositoryListString = response.body()

        val gson = Gson()
        val repositoryList = gson.fromJson(repositoryListString, Array<GitHubRepository>::class.java)

        //Data Visualization
        for (repo: GitHubRepository in repositoryList)
            println("${repo.name} -> ${repo.owner}")

        return SuccessResponse(200, repositoryList)

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

fun buildRequest(url: String, path: String): HttpResponse<String>? {
    val client = HttpClient.newBuilder().build()

    //testing only
    print("$url/$path\n")

    val request = HttpRequest.newBuilder()
        .uri(URI.create("$url/$path"))
        .GET()
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    //testing only
    //print(response.body())

    return response
}