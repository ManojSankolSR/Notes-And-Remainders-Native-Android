package com.example.notesandremainders.auth.domain

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.UserInfo

interface AuthRepository {

    suspend fun signIn(email:String,password:String): AuthResult
    suspend fun signUp(email:String,password:String,userName:String):AuthResult

     fun signOut()

    suspend fun googleSignIn(idToken:String)
}