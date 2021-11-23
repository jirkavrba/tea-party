package dev.vrba.teaparty.engine

import org.springframework.stereotype.Service

@Service
class WordlistService {

    private val wordlist: HashSet<String> by lazy {
        this::class.java.getResource("/wordlist.txt")
                ?.readText()
                ?.lines()
                ?.toHashSet()
                ?: throw IllegalStateException("Cannot read the wordlist file")
    }

    fun isKnownWord(word: String): Boolean = wordlist.contains(word)
}