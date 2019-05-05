package br.com.iolandasantos.crudheroesapp.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.Hero
import br.com.iolandasantos.crudheroesapp.repository.StudioRepository
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroListAdapter(
    val context: Context,
    val heroes: List<Hero>,
    val clickLista: (Hero) -> Unit
) :
    RecyclerView.Adapter<HeroListAdapter.HeroViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HeroViewHolder {

        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.hero_item, p0, false)

        return HeroViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    override fun onBindViewHolder(p0: HeroViewHolder, position: Int) {
        val hero = heroes[position]
        p0.bindView(hero, clickLista)
    }


    class HeroViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindView(
            hero: Hero,
            clickLista: (Hero) -> Unit
        ) = with(itemView) {
            tvName.text = hero.name
            tvStudio.text = hero.studio

            val studioRepository = StudioRepository()

            studioRepository.buscarStudio(hero.studio,onComplete = {
                tvStudio.text = it?.name
            }, onError = {})

            tvPower.text = hero.power
            tvWeakness.text = hero.weakness

            setOnClickListener { clickLista(hero) }
        }

    }
}