package com.dm.crudusuarios.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.crudusuarios.R
import com.dm.crudusuarios.databinding.ActivityHomeBinding
import com.dm.crudusuarios.view.adapters.UserAdapter
import com.dm.crudusuarios.viewmodel.UsuarioViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]
        binding.rvUsers.layoutManager = LinearLayoutManager(this);
        adapter = UserAdapter(emptyList())
        binding.rvUsers.adapter = adapter


        viewModel.users.observe(this) { users ->
            //Toast.makeText(this, "Usuarios cargados: ${users.size}", Toast.LENGTH_SHORT).show()
            adapter.updateData(users)
        }

        viewModel.error.observe(this) {
            Log.e("Error datos ",it)
            //Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchUsers()
        //Toast.makeText(this, "Llamando API...", Toast.LENGTH_SHORT).show()
    }
}