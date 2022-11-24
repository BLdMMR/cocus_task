package cocus.interview.task.data_access

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

    private final val API_URL: String = "https://api.github.com"

    fun getAllUserRepos(username: String) : String? {
        
        var repoName = "projetoeseminario"
        return buildRequest(API_URL, "repos/$username/$repoName/branches")
    }
}

fun buildRequest(url: String, path: String): String? {
    val client = HttpClient.newBuilder().build()
    print("$url/$path\n")
    val request = HttpRequest.newBuilder()
        .uri(URI.create("$url/$path"))
        .GET()
        .build()
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    print(response.body())
    return response.body()
}