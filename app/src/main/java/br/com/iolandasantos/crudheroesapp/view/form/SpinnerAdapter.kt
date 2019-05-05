package br.com.iolandasantos.crudheroesapp.view.form

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.iolandasantos.crudheroesapp.R
import br.com.iolandasantos.crudheroesapp.model.Studio

class SpinnerAdapter internal constructor(internal var context: Context, internal var list: List<Studio>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return list[position]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            val inflater = LayoutInflater.from(context)

            view = inflater.inflate(R.layout.spinner_studio_item, viewGroup, false)
        }

        val textView = view!!.findViewById<TextView>(R.id.textView)

        textView.text = list[i].name

        return textView

    }

    fun getPositionByID(id: String): Int{
        var position : Int = 0

        for(position in 0 until list.size){
            if(list[position]._id.toString().equals(id)) {
                return position
            }
        }
        return position
    }
}