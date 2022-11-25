package cocus.interview.task.structures

import com.fasterxml.jackson.annotation.JsonCreator

class GitHubRepository (var name: String, var owner: GitHubRepositoryOwner, var branches: Array<GitHubBranch>? = null)

class GitHubRepositoryOwner constructor(val login: String)

class GitHubBranch constructor(val name: String, val commit: Commit)

class Commit constructor(var sha: String)


