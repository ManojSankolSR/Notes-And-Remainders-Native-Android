package com.example.notesandremainders

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.notesandremainders.auth.presentation.AuthViewModel
import com.example.notesandremainders.auth.presentation.LoadingFragment
import com.example.notesandremainders.auth.presentation.SignInFragment
import com.example.notesandremainders.databinding.ActivityMainBinding
import com.example.notesandremainders.notes.Presentation.NotesFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        val view=binding.root;
        val viewModel:AuthViewModel by viewModels()
        val authState=viewModel.authState;

        setContentView(view);

      authState.observe(this) { authval ->
            when {

                authval.error != null -> {
                    val loadingFragment = supportFragmentManager.findFragmentByTag("loading");
                    if(loadingFragment!=null){
                        supportFragmentManager.commit {
                            remove(loadingFragment);
                        }
                    }
                    Snackbar.make(binding.root, authval.error, Snackbar.LENGTH_LONG).show()
                }

                authval.loading -> {

                    val loadingFragment = supportFragmentManager.findFragmentByTag("loading");
                    if(loadingFragment==null){
                        supportFragmentManager.commit {
                            add(R.id.main_content, LoadingFragment(),"loading")
                            addToBackStack(null)
                        }
                    }


                }

                authval.user == null -> {

                    supportFragmentManager.commit {
                        replace(R.id.main_content, SignInFragment())

                    }
                }

                else -> {

                    supportFragmentManager.commit {
                        replace(R.id.main_content, NotesFragment())
                        addToBackStack(null)
                    }
                }
            }
        }
    }

//        authState.observe(this, Observer { authval->
//            if(authval.error!=null){
//
//
//                Snackbar.make(binding.root,authval.error,Snackbar.LENGTH_LONG).show();
//
//
//
//            }else if(authval.user==null){
//                supportFragmentManager.commit {
//                    replace(R.id.main_content,SignInFragment());
//
//
//                }
//
//            }else if(authval.loading) {
//                Snackbar.make(binding.root,"Loading.......",Snackbar.LENGTH_LONG).show();
//                supportFragmentManager.commit {
//                    replace(R.id.main_content,LoadingFragment())
//
//                }
//
//            }else{
//                supportFragmentManager.commit {
//                    replace(R.id.main_content,NotesFragment())
//                }
//            }
//        }
//        )
    }
//}