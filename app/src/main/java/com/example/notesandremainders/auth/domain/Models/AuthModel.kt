package com.example.notesandremainders.auth.domain.Models

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import java.lang.Error

data class AuthModel(val user:FirebaseUser ?,val loading:Boolean,val error: String ?) {
}