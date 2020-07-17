package com.example.schoolscientistsexample

import com.google.gson.annotations.SerializedName

class MichaelITeamScope (
    @SerializedName("basket1") private var team1Scope: Int,
    @SerializedName("basket2") private var team2Scope: Int
){
    fun team1ScoreGet() : Int{
        return team1Scope
    }
    fun team2ScoreGet() : Int{
        return team2Scope
    }
}
