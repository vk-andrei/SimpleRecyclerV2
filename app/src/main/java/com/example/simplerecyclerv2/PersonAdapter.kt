package com.example.simplerecyclerv2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplerecyclerv2.databinding.PersonOneItemBinding

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonHolder>() {

    private var persons = arrayListOf<Person>()

    class PersonHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = PersonOneItemBinding.bind(item)
        fun bind(person: Person) = with(binding) {
            tvPersonName.text = person.name
            ivPersonImageId.setImageResource(person.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.person_one_item, parent, false)
        return PersonHolder(view)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(persons[position])
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    fun addPerson(person: Person) {
        persons.add(person)
        notifyItemChanged(persons.size - 1)
    }
}