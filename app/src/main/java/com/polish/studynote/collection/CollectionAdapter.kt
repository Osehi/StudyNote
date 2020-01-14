package com.polish.studynote.collection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.polish.studynote.R
import com.polish.studynote.database.Task
import kotlinx.android.synthetic.main.list_item.view.*

class CollectionAdapter: RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {


    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return data.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]
        holder.urTopic.text = item.topic
        holder.urDate.text = item.date
        holder.urLocation.text = item.location
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val urTopic:TextView = itemView.findViewById(R.id.screenTopicId)
        val urDate:TextView = itemView.findViewById(R.id.screenDateId)
        val urLocation:TextView = itemView.findViewById(R.id.screenLocateId)


    }

}