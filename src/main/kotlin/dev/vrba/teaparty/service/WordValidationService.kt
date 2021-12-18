package dev.vrba.teaparty.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class WordValidationService(@Value("\${wordlist.path:/wordlist.txt}") path: String) {

    private val words: Set<String> = javaClass.getResource(path)
        ?.readText()
        ?.lines()
        ?.map { it.trim().lowercase() }
        ?.filter { it.length >= 3 }
        ?.toSet() ?: throw IllegalArgumentException("Cannot find the specified wordlist resource [$path]")

    fun isValidWord(word: String): Boolean = words.contains(word.lowercase())
}