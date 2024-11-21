import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerassignment2.R

class RecyclerAdapter(
    private val items: MutableList<Item>,
    private val onItemLongClick: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit,
    private val onSwipeLeft: (Int) -> Unit,
    private val onSwipeRight: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.itemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemText.text = item.name


        holder.itemText.setBackgroundColor(
            if (item.isSelected) Color.LTGRAY else Color.WHITE
        )

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick(position)
            true
        }
    }

    override fun getItemCount() = items.size
}