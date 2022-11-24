package cocus.interview.task.structures

import com.fasterxml.jackson.annotation.JsonCreator

class GitHubRepositoryOwner @JsonCreator constructor(val login: String) {
    override fun toString() : String{
        return login
    }
}