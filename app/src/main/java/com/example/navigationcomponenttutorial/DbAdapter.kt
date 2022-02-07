package com.example.navigationcomponenttutorial

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponenttutorial.model.Students

class DbAdapter(
    val context: Context,
    val dbClickDeleteInterface: DbClickDeleteInterface,
    val dbClickUpdateInterface: DbClickUpdateInterface,
    ) : RecyclerView.Adapter<DbAdapter.ViewHolder>() {

    private var allStds = ArrayList<Students>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val email = itemView.findViewById<TextView>(R.id.item_email)
        val update = itemView.findViewById<ImageView>(R.id.item_update)
        val delete = itemView.findViewById<ImageView>(R.id.item_del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(allStds.get(position).stdName)
        holder.email.setText(allStds.get(position).stdEmail)

        holder.update.setOnClickListener {
            dbClickUpdateInterface.onUpdateIconClick(allStds.get(position))
        }

        holder.delete.setOnClickListener {
            dbClickDeleteInterface.onDeleteIconClick(allStds.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allStds.size
    }

    fun setData(user: ArrayList<Students>){
        this.allStds=user
        notifyDataSetChanged()
    }

    interface DbClickUpdateInterface {
        fun onUpdateIconClick(students: Students)
    }

    interface DbClickDeleteInterface {
        fun onDeleteIconClick(students: Students)
    }

}