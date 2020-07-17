package com.example.schoolscientistsexample

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking

class ServerCommandMichaelI {

    private val client = HttpClient()

    private suspend fun gameScoreGetBody(): String{
        try{
            var query = "https://ms5.newtonbox.ru/get/sporthall1/"
            val res = client.get<String>(query)
            Log.i(query + " Simple case ", res)
            return res
        }
        catch (th : Throwable) {
            return "ОШИБКА"
        }
    }

    fun gameScoreGet(): String{
        return runBlocking { gameScoreGetBody() }
    }

    private suspend fun ResetCountBody(): String{
        try{
            var query = "https://ms5.newtonbox.ru/clear"
            val res = client.get<String>(query)
            Log.i(query + " Simple case ", res)
            return res
        }
        catch (th : Throwable) {
            return "ОШИБКА"
        }
    }

    fun ResetCount(): String{
        return runBlocking {ResetCountBody() }
    }
}