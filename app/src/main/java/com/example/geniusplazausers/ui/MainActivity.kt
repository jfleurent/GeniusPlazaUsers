package com.example.geniusplazausers.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geniusplazausers.networking.NetworkingService
import com.example.geniusplazausers.R
import com.example.geniusplazausers.models.User
import com.example.geniusplazausers.adapter.UserRecyclerviewAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.users_recyclerview)
        CoroutineScope(Dispatchers.Main).addToRecyclerView()

        fab.setOnClickListener { view ->
            Dialog(this@MainActivity, R.style.DialogTheme).apply {
                setContentView(R.layout.user_dialog)
                val name = findViewById<TextInputEditText>(R.id.name_text)
                val job = findViewById<TextInputEditText>(R.id.job_text)
                val cancel = findViewById<MaterialButton>(R.id.cancel_button)
                val ok = findViewById<MaterialButton>(R.id.ok_button)
                ok.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).postUser(
                        name.text.toString(),
                        job.text.toString()
                    )
                    dismiss()
                }
                cancel.setOnClickListener { dismiss() }
                show()
            }
        }
    }

    fun CoroutineScope.postUser(name: String, job: String){
        launch {
           Toast.makeText(this@MainActivity
               , NetworkingService.getUserApi().addUser(name, job).toString(),
               Toast.LENGTH_LONG )
               .show()
        }
    }

    fun CoroutineScope.addToRecyclerView(){
        launch {
            recyclerview.apply {
                val users = ArrayList<User>()
                val initialResponse = NetworkingService.getUserApi().getUsers(1)
                users.addAll(initialResponse.data)
                for(i in 2..initialResponse.totalPages) {
                    users.addAll(NetworkingService.getUserApi().getUsers(i).data)
                }
                adapter = UserRecyclerviewAdapter(users)
                layoutManager = LinearLayoutManager(this@MainActivity)
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                )
            }
        }
    }
}
