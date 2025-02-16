import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orion.ri.RIApplication
import com.orion.ri.activities.dashboard.DataStoreKeys
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.model.response.ProjectResponse
import com.orion.ri.model.response.TaskResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private const val DATA_STORE_NAME = "app_data_store"

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class DataStoreHelper(private val context: Context) {


    fun saveCurrentUserProfile(employee1: EmployeesResponse?) {
        runBlocking {
            appDataStore.edit { settings ->
                val gson = Gson()
                val employeeString = gson.toJson(employee1)
                settings[DataStoreKeys.CURRENT_USER_DATA] = employeeString
            }
        }

    }

    fun getCurrentUserProfile(): EmployeesResponse {
        val item = runBlocking {
            val stringEmployee = appDataStore.data.first()[DataStoreKeys.CURRENT_USER_DATA]
            val gson = Gson()
            val employee: EmployeesResponse =
                gson.fromJson(stringEmployee, EmployeesResponse::class.java)
            return@runBlocking employee
        }
        return item
    }

    fun saveCurrentUserType(userType: String) {
        runBlocking {
            appDataStore.edit { settings ->
                settings[DataStoreKeys.USER_TYPE] = userType
            }
        }

    }

    fun getCurrentUserType(): String {
        val item = runBlocking {
            return@runBlocking appDataStore.data.first()[DataStoreKeys.USER_TYPE]
        }
        return item ?: "employee"
    }


    fun saveAllUsers(employees: List<EmployeesResponse>) {
        runBlocking {
            appDataStore.edit { settings ->
                val gson = Gson()
                val employeesList = gson.toJson(employees)
                settings[DataStoreKeys.EMPLOYEES_LIST] = employeesList
            }
        }
    }


    fun getAllUsers(): List<EmployeesResponse> {
        val items = runBlocking {
            return@runBlocking try {
                val stringEmployeesList = appDataStore.data.first()[DataStoreKeys.EMPLOYEES_LIST]
                if (stringEmployeesList.isNullOrEmpty()) {
                    emptyList()
                } else {
                    val gson = Gson()
                    val itemType = object : TypeToken<List<EmployeesResponse>>() {}.type
                    gson.fromJson<List<EmployeesResponse>>(stringEmployeesList, itemType)
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
        return items
    }



    fun saveAllProjects(projects: List<ProjectResponse>?) {
        runBlocking {
            appDataStore.edit { settings ->
                val gson = Gson()
                val projectsList = gson.toJson(projects)
                settings[DataStoreKeys.PROJECTS_LIST] = projectsList
            }
        }
    }

    fun getAllProjects(): List<ProjectResponse> {
        val projects = runBlocking {
            return@runBlocking try {
                val stringProjectsList = appDataStore.data.first()[DataStoreKeys.PROJECTS_LIST]
                if (stringProjectsList.isNullOrEmpty()) {
                    emptyList()
                } else {
                    val gson = Gson()
                    val itemType = object : TypeToken<List<ProjectResponse>>() {}.type
                    gson.fromJson<List<ProjectResponse>>(stringProjectsList, itemType)
                }
            } catch (e: Exception) {
                emptyList()
            }
        }
        return projects

    }
    fun saveAllTasks(tasks: List<TaskResponse>) {
        runBlocking {
            appDataStore.edit { settings->
                val gson = Gson()
                val tasksList = gson.toJson(tasks)
                settings[DataStoreKeys.TASKS_LIST] = tasksList
            }
        }
    }

    fun getAllTasks(): List<TaskResponse>{
        val tasks = runBlocking {
            return@runBlocking try {
                val stringTasksList = appDataStore.data.first()[DataStoreKeys.TASKS_LIST]
                if (stringTasksList.isNullOrEmpty()){
                    emptyList()
                }else{
                    val gson = Gson()
                    val itemType = object : TypeToken<List<TaskResponse>>() {}.type
                    gson.fromJson<List<TaskResponse>>(stringTasksList, itemType)
                }
            }catch (e:Exception){
                emptyList()
            }
        }
        return tasks
    }

    companion object {
        @Volatile
        private var INSTANCE: DataStoreHelper? = null

        fun getInstance(): DataStoreHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = DataStoreHelper(RIApplication.context)
                INSTANCE = instance
                instance
            }
        }
    }

    val appDataStore: DataStore<Preferences> by lazy {
        context.appDataStore
    }
}