package cocus.interview.task.structures

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/*
class GitHubRepository @JsonCreator constructor(@JsonProperty val name: String, @JsonProperty val owner: String) {



}
*/
class GitHubRepository (var name: String, var owner: GitHubRepositoryOwner)