package com.example.notesandremainders.notes.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.notesandremainders.R
import com.example.notesandremainders.auth.presentation.AuthViewModel
import com.example.notesandremainders.databinding.FragmentNotesBinding
import com.example.notesandremainders.databinding.FragmentSignInBinding

class NotesFragment : Fragment() {

    private  lateinit var _binding: FragmentNotesBinding;
    private val _authViewModel:AuthViewModel by activityViewModels();


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentNotesBinding.inflate(inflater,container,false);
        val view=_binding.root;
        return  view;
           }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.topAppBar.setOnMenuItemClickListener {
            item->
            when (item.itemId) {
//                R.id.menuIcon -> {
//                    // Handle icon click here
//                    true
//                }
                R.id.LogOutIcon -> {
                    _authViewModel.signOut();
                    true;
                }
                else -> false
            }
        }
    }


}