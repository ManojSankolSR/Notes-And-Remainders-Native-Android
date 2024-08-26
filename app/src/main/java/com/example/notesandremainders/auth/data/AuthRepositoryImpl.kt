package com.example.notesandremainders.auth.data

import com.example.notesandremainders.auth.domain.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl:AuthRepository {


    private val _firebaseAuth:FirebaseAuth = FirebaseAuth.getInstance();


    override suspend fun signIn(email:String,password:String):AuthResult {
        return  _firebaseAuth.signInWithEmailAndPassword(email,password).await();

    }

    override suspend fun signUp(email:String,password:String,userName:String):AuthResult {
        val authResult= _firebaseAuth.createUserWithEmailAndPassword(email,password).await();
        _firebaseAuth.currentUser?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(userName).build())?.await();
        return  authResult;
    }

    override fun signOut() {
        _firebaseAuth.signOut()
    }

    override suspend fun googleSignIn(idToken: String) {

        val gso:GoogleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(
                    "AIzaSyCtLX-NLyvbpG_oov9KnVFlPkTInRdEpsE")
            .requestEmail()
                .build();



val gCredential=GoogleAuthProvider.getCredential(idToken,null);
        _firebaseAuth.signInWithCredential(gCredential);

    }


}