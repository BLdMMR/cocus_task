package cocus.interview.task

import cocus.interview.task.data_access.ACCESS_TOKEN
import cocus.interview.task.data_access.MainDataAccess
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskApplication

fun main(args: Array<String>) {
	if (!args.isEmpty())
		ACCESS_TOKEN = args[0]
	runApplication<TaskApplication>(*args)
}
