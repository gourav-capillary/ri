import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class MultiSelectAdapter(context: Context, resource: Int, private val items: List<String>) :
    ArrayAdapter<String>(context, resource, items) {

    private val selectedItems = mutableListOf<String>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)

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
                selectedItems.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<String> {
        return selectedItems.toList()
    }
}
