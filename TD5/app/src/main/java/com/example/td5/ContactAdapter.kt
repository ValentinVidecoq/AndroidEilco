package com.example.td5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.bumptech.glide.Glide

class ContactAdapter(var mContacts: MutableList<Contact>) : Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var firstNameTextView: TextView
        var lastNameTextView: TextView

        init {
            firstNameTextView = itemView.findViewById(R.id.textView1)
            lastNameTextView = itemView.findViewById(R.id.textView2)
        }
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var context: Context = parent.context
        var inflater: LayoutInflater = LayoutInflater.from(context)

        var contactView: View = inflater.inflate(R.layout.item_contact, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact: Contact = mContacts[position]

        var firstNameTextView: TextView = holder.firstNameTextView
        firstNameTextView.text = contact.prenom

        var lastNameTextView: TextView = holder.lastNameTextView
        lastNameTextView.text = contact.nom

        var imageView: ImageView = holder.itemView.findViewById(R.id.imageView)

        Glide.with(holder.itemView).load(contact.imageUrl).into(imageView)


    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    fun contactAdapter(contacts: MutableList<Contact>){
        mContacts = contacts
    }
}