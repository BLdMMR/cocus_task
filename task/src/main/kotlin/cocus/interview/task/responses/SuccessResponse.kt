package cocus.interview.task.responses

import cocus.interview.task.structures.GitHubRepository

class SuccessResponse(override val status: String, override val message: Array<GitHubRepository>) : ResponseMessage {
}