package com.example.flowtogether

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.flowtogether.ui.CustomButtons

class DayTasksActivity : AppCompatActivity() {

    private val tasks = mutableListOf<String>()
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_tasks)

        // Configurar RecyclerView
        val tasksRecyclerView = findViewById<RecyclerView>(R.id.tasksRecyclerView)
        tasksAdapter = TasksAdapter(tasks)
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        tasksRecyclerView.adapter = tasksAdapter

        // Mostrar la fecha seleccionada
        val selectedDate = intent.getStringExtra("selected_date") ?: "Fecha no especificada"
        findViewById<TextView>(R.id.dateText).text = "Tareas del día: $selectedDate"

        // Renderizar los botones personalizados en ComposeView
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            CustomButtons(
                onAddTaskClick = {
                    val newTask = "Tarea para $selectedDate - ${tasks.size + 1}"
                    tasks.add(newTask)
                    tasksAdapter.notifyItemInserted(tasks.size - 1)
                    Log.d("DayTasksActivity", "Tarea añadida: $newTask")
                },
                onBackClick = {
                    finish()
                    Log.d("DayTasksActivity", "Volviendo al calendario")
                }
            )
        }
    }
}
