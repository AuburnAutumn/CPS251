package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.CardLayoutBinding  // Replace with your actual package name

//Pass in MainViewModel to access its methods
class ContactListAdapter(private val mainViewModel: MainViewModel) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private var contactList: List<Contact>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {
        val contact = contactList?.get(listPosition)
        holder.binding.contactName.text = contact?.contactName
        holder.binding.contactNumber.text = contact?.contactNumber
        val contactID = contact?.id

        //Calls the delete method from MainViewModel if the Contact isn't null and passes in its ID
        holder.binding.deleteButton.setOnClickListener {
            if (contactID != null) {
                mainViewModel.deleteContact(contactID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    fun setContactList(contacts: List<Contact>?) {
        contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return contactList?.size ?: 0
    }

    class ViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}