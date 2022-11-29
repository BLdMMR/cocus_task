package cocus.interview.task.structures

import java.util.*

interface OutputEntity
class GitHubRepository (var name: String, var owner: GitHubRepositoryOwner, var branches: Array<GitHubBranch>? = null, val fork: Boolean)

class GitHubRepositoryOwner constructor(val login: String)

class GitHubBranch constructor(val name: String, val commit: Commit)

class Commit constructor(var sha: String)

class ErrorResponse(val status: Int, val message: String) : OutputEntity

class GitHubRepositoryList(val githubRepoList: LinkedList<GitHubRepository>) : OutputEntity 
