package com.example.cpbyte_portal.util

import android.net.Uri

object Validator {

    fun isValidLibraryId(id: String): Boolean {
        if (id.isBlank()) return false
        if (id.length > 15) return false
        val regex = "^[A-Za-z0-9()]+$".toRegex()
        return regex.matches(id)
    }

    fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) return false
        if (password.length < 6) return false
        return true
    }

    fun isValidGithubUrl(url: String): Boolean {
        val githubRepoRegex = Regex(
            pattern = """^https://github\.com/[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+/?$|^http://github\.com/[A-Za-z0-9_.-]+/[A-Za-z0-9_.-]+/?$""",
            options = setOf(RegexOption.IGNORE_CASE)
        )
        return url.trim().matches(githubRepoRegex)
    }

    fun isValidProjectForm(
        projectName: String,
        githubUrl: String,
        description: String,
        imageUri: Uri?,
    ): Boolean {
        return projectName.isNotBlank()
                && githubUrl.isNotBlank()
                && description.isNotBlank()
                && imageUri != null
    }

    //A LeetCode username must be between 3 and 20 characters long, can only contain alphanumeric characters and underscores, and must start with an alphabetic character.
    fun isValidLeetCodeUsername(username: String): Boolean {
        val trimmed = username.trim()
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9_]{2,19}$")
        return regex.matches(trimmed)
    }

    // A valid GitHub username can contain only alphanumeric characters (A-Z, a-z, 0-9) and hyphens (-)
    fun isValidGitHubUsername(username: String): Boolean {
        val trimmed = username.trim()

        if (trimmed.isEmpty() || trimmed.length > 39) return false
        if (trimmed.startsWith("-") || trimmed.endsWith("-")) return false
        if ("--" in trimmed) return false

        val regex = Regex("^[a-zA-Z0-9-]+\$")
        return regex.matches(trimmed)
    }
}

