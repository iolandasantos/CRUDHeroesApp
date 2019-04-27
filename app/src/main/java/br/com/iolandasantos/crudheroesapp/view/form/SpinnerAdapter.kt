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

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            val inflater = LayoutInflater.from(context)

            view = inflater.inflate(R.layout.item, viewGroup, false)
        }

        val textView = view!!.findViewById<TextView>(R.id.textView)

        textView.text = list[i]._id + " " + list[i].name

        return textView

    }
}