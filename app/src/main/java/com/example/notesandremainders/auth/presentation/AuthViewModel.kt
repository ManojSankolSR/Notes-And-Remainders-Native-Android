package com.example.notesandremainders.auth.presentation

import android.media.MediaCodec
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesandremainders.auth.data.AuthRepositoryImpl
import com.example.notesandremainders.auth.domain.Models.AuthModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class AuthViewModel(private val _authRepositoryImpl:AuthRepositoryImpl=AuthRepositoryImpl()) : ViewModel() {

    val userName=MutableLiveData<String>("");
//    val email=MutableLiveData<String>("");
//    val password=MutableLiveData<String>("");



    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?> get() = _emailError

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?> get() = _passwordError

    private  val _userNameError=MutableLiveData<String?>()
    val userNameError:LiveData<String?> get() = _userNameError;







    private val _firebaseAuth=FirebaseAuth.getInstance();

    private val _authState = MutableLiveData<AuthModel>(AuthModel(user = null, loading = false, error = null )) ;

    val authState:LiveData<AuthModel> = _authState;

    private val authStateListener=FirebaseAuth.AuthStateListener {
        auth-> _authState.value= _authState.value?.copy(user = auth.currentUser)

    }

    init {
        _firebaseAuth.addAuthStateListener(authStateListener);

    }

    fun validateAndSignIn(email:String,password:String){
        if(email.trim().isNullOrEmpty() ){
             _emailError.value ="Please Enter Some Value"
        }else if(password.trim().isNullOrEmpty()) {
            _passwordError.value ="Please Enter Some Value"

        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value="Enter A Valid Email"



        }else if(password.length<=6){
            _passwordError.value="Password Length Shoud Be Greater Than 6"

        }else{
            viewModelScope.launch {
                try {



                        _authState.value=_authState.value?.copy(loading = true, error = null)
                        val authResult =  _authRepositoryImpl.signIn(email, password);
                        _authState.value=_authState.value?.copy(user = authResult.user, loading = false,);

                }catch (e:FirebaseAuthException){

                    _authState.value=_authState.value?.copy(loading = false, error = e.message);




                }
            }
//

        }
    }

    fun signOut(){
        _authRepositoryImpl.signOut()
    }



    fun validateAndSignUp(userName:String,email:String,password: String){

        if(email.trim().isNullOrEmpty() ){
            _emailError.value ="Please Enter Some Value"
        }else if(password.trim().isNullOrEmpty()) {
            _passwordError.value ="Please Enter Some Value"

        }else if(userName.isNullOrEmpty()){
            _userNameError.value="Please Enter Some Value"

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailError.value="Enter A Valid Email"



        }else if(password.length<=6){
            _passwordError.value="Password Length Shoud Be Greater Than 6"

        }else{
            viewModelScope.launch {
                _authRepositoryImpl.signUp(email = email,password=password,userName=userName);
            }
        }


    }




}