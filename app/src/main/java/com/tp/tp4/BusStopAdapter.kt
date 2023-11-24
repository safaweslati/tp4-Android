package com.tp.tp4
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tp.tp4.database.entities.Schedule
import java.text.SimpleDateFormat
import java.util.*

class BusStopAdapter( private var scheduleList: List<Schedule> = emptyList(),
                      private val onItemClick: (Schedule) -> Unit = {}) :
    RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopName: TextView = itemView.findViewById(R.id.stopNameTextView)
        val arrivalTime: TextView = itemView.findViewById(R.id.arrivalTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bus_stop, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSchedule = scheduleList[position]
        holder.stopName.text = currentSchedule.stop_name
        val formattedArrivalTime = formatArrivalTime(currentSchedule.arrival_time.toLong())
        holder.arrivalTime.text = formattedArrivalTime
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentSchedule)
        }
    }

    fun formatArrivalTime(timestamp: Long): String {
        val date = Date(timestamp * 1000L)
        val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
        return sdf.format(date)
    }


    override fun getItemCount(): Int {
        return scheduleList.size
    }

    fun updateList(list: List<Schedule>) {
        this.scheduleList = list
        notifyDataSetChanged()
    }
}
