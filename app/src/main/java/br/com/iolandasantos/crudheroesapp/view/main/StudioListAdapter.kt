package br.com.iolandasantos.crudheroesapp.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.Studio
import kotlinx.android.synthetic.main.studio_item.view.*

class StudioListAdapter(
    val context: Context,
    val studios: List<Studio>,
    val clickLista: (Studio) -> Unit
) :
    RecyclerView.Adapter<StudioListAdapter.StudioViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StudioViewHolder {

        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.studio_item, p0, false)

        return StudioViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studios.size
    }

    override fun onBindViewHolder(p0: StudioViewHolder, position: Int) {
        val studio = studios[position]
        p0.bindView(studio, clickLista)
    }


    class StudioViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(
            studio: Studio,
            clickLista: (Studio) -> Unit
        ) = with(itemView) {
            tvName.text = studio._id + " - " + studio.name
            tvHeadquarter.text = studio.headquarter
            tvWebsite.text = studio.website

            setOnClickListener { clickLista(studio) }
        }

    }
}