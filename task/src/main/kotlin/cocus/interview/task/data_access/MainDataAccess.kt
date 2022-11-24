package cocus.interview.task.data_access

import cocus.interview.task.structures.GitHubRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
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

    fun getAllUserRepositories(username: String) : Array<GitHubRepository>? {
        val repoString = buildRequest(API_URL, "users/$username/repos")


        val gson = Gson()
        val om = ObjectMapper()

        val repositoryList = gson.fromJson<Array<GitHubRepository>>(repoString, Array<GitHubRepository>::class.java)
        for (repo: GitHubRepository in repositoryList)
            println("${repo.name} -> ${repo.owner}")

        return repositoryList

    }

    fun getAllRepositoryBranches(username: String, repositoryName: String) :String? {
        return buildRequest(API_URL, "repos/$username/$repositoryName/branches")
    }
}

fun buildRequest(url: String, path: String): String? {
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

    return response.body()
}