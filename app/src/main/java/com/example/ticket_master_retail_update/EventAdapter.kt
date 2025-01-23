import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticket_master_retail_update.R
import com.example.ticket_master_retail_update.api.Event

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventName: TextView = itemView.findViewById(R.id.eventName)
        private val eventDate: TextView = itemView.findViewById(R.id.eventDate)
        private val eventImage: ImageView = itemView.findViewById(R.id.eventImage)

        @SuppressLint("SetTextI18n")
        fun bind(event: Event) {
            eventName.text = event.name
            eventDate.text = "${event.dates.start.localDate} - ${event.dates.start.localTime}"
//            Log.d("url",event.url)
//            eventImage.setImageURI(Uri.parse(event.url))
            Glide.with(itemView.context)
                .load(event.images[0].url)  // Assuming the image URL is stored in 'url' property (update if needed)
                .placeholder(R.drawable.placeholder_image)  // Optionally set a placeholder
                .error(R.drawable.placeholder_image)  // Optionally set an error image
                .into(eventImage)
        }
    }
}
