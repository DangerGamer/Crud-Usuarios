package com.dm.crudusuarios.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
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
    private var isMenuOpen = false
    private var wasMoved = false
    private var fabInitialX = 0f
    private var fabInitialY = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initUi() {
        viewModel = ViewModelProvider(this)[UsuarioViewModel::class.java]

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i("Buscando", p0.toString())
                viewModel.fetchUsersByFilter(p0.toString())
            }
        });

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchUsers()
        }

        viewModel.users.observe(this) {
            adapter.updateData(it)
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.error.observe(this) {
            binding.swipeRefresh.isRefreshing = false
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


        binding.rvUsers.layoutManager = LinearLayoutManager(this);
        adapter = UserAdapter(this, emptyList())
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


        binding.faAcciones.post {
            fabInitialX = binding.faAcciones.translationX
            fabInitialY = binding.faAcciones.translationY
        }

        binding.faAcciones.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    wasMoved = false
                    val startX = view.translationX
                    val startY = view.translationY
                    val touchX = event.rawX
                    val touchY = event.rawY
                    view.tag = floatArrayOf(startX, startY, touchX, touchY)
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    wasMoved = true
                    val tag = view.tag as? FloatArray ?: return@setOnTouchListener true
                    val startX = tag[0]
                    val startY = tag[1]
                    val touchX = tag[2]
                    val touchY = tag[3]

                    val dx = event.rawX - touchX
                    val dy = event.rawY - touchY

                    view.translationX = startX + dx
                    view.translationY = startY + dy
                    true
                }

                MotionEvent.ACTION_UP -> {
                    if (!wasMoved) {
                        // Toggle menú de acciones
                        isMenuOpen = !isMenuOpen

                        val fabPairs = listOf(
                            binding.faCrearUsuario to binding.tvCrearUsuario,
                            binding.faEliminar to binding.tvEliminar,
                        )

                        //oclicksUnificados
                        val crearUsuarioClick = View.OnClickListener {
                            abrirCrearUsuario()
                        }
                        binding.faCrearUsuario.setOnClickListener(crearUsuarioClick)
                        binding.tvCrearUsuario.setOnClickListener(crearUsuarioClick)

                        val eliminarUsuarioClick = View.OnClickListener {
                            //eliminarSeleccionados()
                        }
                        binding.faEliminar.setOnClickListener(eliminarUsuarioClick)
                        binding.tvEliminar.setOnClickListener(eliminarUsuarioClick)

                        fabPairs.forEachIndexed { index, (fab, label) ->
                            fab.visibility = View.VISIBLE
                            label.visibility = View.VISIBLE

                            val offset = (index + 1) * 100f
                            val alpha = if (isMenuOpen) 1f else 0f
                            val translationY = if (isMenuOpen) 0f else offset

                            fab.animate()
                                .alpha(alpha)
                                .translationY(translationY)
                                .setDuration(200)
                                .withEndAction {
                                    if (!isMenuOpen) fab.visibility = View.GONE
                                }
                                .start()

                            label.animate()
                                .alpha(alpha)
                                .translationY(translationY)
                                .setDuration(200)
                                .withEndAction {
                                    if (!isMenuOpen) label.visibility = View.GONE
                                }
                                .start()
                        }

                        binding.faAcciones.animate()
                            .rotation(if (isMenuOpen) 45f else 0f)
                            .setDuration(200)
                            .start()
                    }
                    true
                }

                else -> false
            }
        }
    }

    private fun abrirCrearUsuario() {
        val intent = Intent(this, AdministrarUsuario::class.java)
        intent.putExtra("crud","crear")
        startActivity(intent)
    }

    private fun reestablecerAcciones() {
        isMenuOpen = false

        binding.faAcciones.animate()
            .rotation(0f)
            .setDuration(150)
            .start()

        binding.faAcciones.animate()
            .translationX(fabInitialX)
            .translationY(fabInitialY)
            .setDuration(200)
            .start()

        listOf(
            binding.faCrearUsuario,
            binding.faEliminar,
            binding.tvCrearUsuario,
            binding.tvEliminar
        ).forEach {
            it.visibility = View.GONE
            it.alpha = 0f
        }
    }



    override fun onResume() {
        super.onResume()
        viewModel.fetchUsers()
        reestablecerAcciones()
    }
}