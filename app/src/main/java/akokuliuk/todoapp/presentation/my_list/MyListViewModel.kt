package akokuliuk.todoapp.presentation.my_list

import akokuliuk.todoapp.domain.models.Task
import akokuliuk.todoapp.domain.usecases.GetTasksUseCase
import akokuliuk.todoapp.presentation.base.FluxViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.launch
import javax.inject.Inject


class MyListViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase
) : FluxViewModel<MyListState>() {

    override fun provideInitialState() = MyListMutableState()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeEvent() {
        if (store.state.tasks.isNullOrEmpty()) {
            store.dispatch {
                it.mutate<MyListMutableState>().apply {
                    showNoTasksLabel = true
                }
            }
        }

        launch {
            val loadedTasks = getTasksUseCase.getTasks()
            store.dispatch {
                it.mutate<MyListMutableState>().apply {
                    showNoTasksLabel = loadedTasks.isEmpty()
                    tasks = loadedTasks
                }
            }
        }
    }


    fun setTaskDone(task: Task, isTaskDone: Boolean) {

    }

    fun onTaskClick(task: Task) {}
}