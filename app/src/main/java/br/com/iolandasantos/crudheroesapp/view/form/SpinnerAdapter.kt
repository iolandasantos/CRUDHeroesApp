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
        var viewLocal = view
        if (viewLocal == null) {
            val inflater = LayoutInflater.from(context)

            viewLocal = inflater.inflate(R.layout.spinner_studio_item, viewGroup, false)
        }

        val textView = viewLocal!!.findViewById<TextView>(R.id.textView)

        textView.text = list[i].name

        return textView

    }

    fun getPositionByID(id: String): Int{
        for(positionByID in 0 until list.size){
            if(list[positionByID]._id.toString() == id) {
                return positionByID
            }
        }
        return 0
    }
}