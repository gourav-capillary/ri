import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.orion.ri.model.response.EmployeesResponse

class MultiSelectAdapter(context: Context, resource: Int, private val items: List<EmployeesResponse>) :
    ArrayAdapter<EmployeesResponse>(context, resource, items) {

    private val selectedItems = mutableListOf<EmployeesResponse>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)?.name

        // Highlight selected items
        if (selectedItems.contains(getItem(position))) {
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark))
        } else {
            textView.setTextColor(Color.BLACK)
        }

        return view
    }

    fun toggleSelection(position: Int) {
        val item = getItem(position)
        if (selectedItems.contains(item)) {
            selectedItems.remove(item)
        } else {
            if (item != null) {
                item.let { selectedItems.add(it) }
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<EmployeesResponse> {
        return selectedItems.toList()
    }
}
