import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orion.ri.RIApplication
import com.orion.ri.activities.dashboard.DataStoreKeys
import com.orion.ri.model.employee.EmployeeDataClass
import com.orion.ri.model.project.ProjectsDataItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private const val DATA_STORE_NAME = "app_data_store"

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class DataStoreHelper(private val context: Context) {


    fun saveCurrentEmployeeProfile(employee1: EmployeeDataClass) {
        runBlocking {
            appDataStore.edit { settings ->
                val gson = Gson()
                val employeeString = gson.toJson(employee1)
                settings[DataStoreKeys.CURRENT_USER_DATA] = employeeString
            }
        }

    }

    suspend fun getCurrentEmployeeProfile(): EmployeeDataClass {
        val stringEmployee = appDataStore.data.first()[DataStoreKeys.CURRENT_USER_DATA]
        val gson = Gson()
        val employee: EmployeeDataClass =
            gson.fromJson(stringEmployee, EmployeeDataClass::class.java)
        return employee
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
        return item?:"employee"
    }

    fun saveProjects(projects: List<ProjectsDataItem>) {
        runBlocking {
            appDataStore.edit { settings ->
                val gson = Gson()
                val projectsList = gson.toJson(projects)
                settings[DataStoreKeys.PROJECTS_LIST] = projectsList
            }
        }
    }

    suspend fun getProjects(): List<ProjectsDataItem> {
        return try {
            val stringProjectsList = appDataStore.data.first()[DataStoreKeys.PROJECTS_LIST]
            if (stringProjectsList.isNullOrEmpty()) {
                emptyList()
            } else {
                val gson = Gson()
                val itemType = object : TypeToken<List<ProjectsDataItem>>() {}.type
                gson.fromJson<List<ProjectsDataItem>>(stringProjectsList, itemType)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun saveAllUsers(employees: List<EmployeeDataClass>) {
        runBlocking {
            appDataStore.edit { settings->
                val gson = Gson()
                val employeesList = gson.toJson(employees)
                settings[DataStoreKeys.EMPLOYEES_LIST] = employeesList
            }
        }
    }


    suspend fun getAllUsers(): List<EmployeeDataClass> {
        return try {
            val stringEmployeesList = appDataStore.data.first()[DataStoreKeys.EMPLOYEES_LIST]
            if (stringEmployeesList.isNullOrEmpty()) {
                emptyList()
            } else {
                val gson = Gson()
                val itemType = object : TypeToken<List<EmployeeDataClass>>() {}.type
                gson.fromJson<List<EmployeeDataClass>>(stringEmployeesList, itemType)
            }
        } catch (e: Exception) {
            emptyList()
        }
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