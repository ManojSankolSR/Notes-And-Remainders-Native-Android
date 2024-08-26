package com.example.notesandremainders.auth.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Fade
import android.transition.Scene
import android.transition.Slide
import android.transition.Transition
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.notesandremainders.R
import com.example.notesandremainders.databinding.FragmentSignInBinding
import com.google.android.material.transition.MaterialSharedAxis

class SignInFragment : Fragment() {


    private val _authViewModel:AuthViewModel by activityViewModels();
    private lateinit var  _binding: FragmentSignInBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */false)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
    }






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentSignInBinding.inflate(inflater,container,false);
        val root=_binding.root;

        return  root;



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)










        _binding.signinButton.setOnClickListener {
            val email = _binding.emailTIF.text.toString()
            val password = _binding.passwordTIF.text.toString()

            _authViewModel.validateAndSignIn(email, password)
        }

        _binding.googleSigninButton.setOnClickListener {

        }

        _binding.dntHaveAccBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main_content,SignUpFragment())
            }
        }



        _authViewModel.passwordError.observeForever {
            value -> _binding.passwordTIF.error=value;
        }

        _authViewModel.emailError.observeForever {
                value -> _binding.emailTIF.error=value;
        }
    }




}