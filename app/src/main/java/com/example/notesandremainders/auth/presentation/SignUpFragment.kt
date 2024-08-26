package com.example.notesandremainders.auth.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.notesandremainders.R
import com.example.notesandremainders.databinding.FragmentSignUpBinding
import com.google.android.material.transition.MaterialSharedAxis


class SignUpFragment : Fragment() {
    lateinit var _binding: FragmentSignUpBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentSignUpBinding.inflate(layoutInflater,container,false);

        return _binding.root;


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.alreadyHaveAccBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.main_content,SignInFragment());
            }




        }


    }


}