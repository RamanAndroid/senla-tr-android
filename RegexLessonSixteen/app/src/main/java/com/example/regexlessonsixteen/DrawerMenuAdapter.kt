package com.example.regexlessonsixteen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.regexlessonsixteen.databinding.RowDrawerBinding

class DrawerMenuAdapter(
    private val inflater: LayoutInflater,
    private val item: List<DrawerItem>
) : BaseAdapter() {

    private class DrawerMenuViewHolder(binding: RowDrawerBinding) {
        val itemTextView = binding.drawerItemTextView
        val itemIcon = binding.drawerItemImage
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val menuViewHolder: DrawerMenuViewHolder?

        if (convertView?.tag == null) {
            val binding = RowDrawerBinding.inflate(inflater)
            view = binding.root
            menuViewHolder = DrawerMenuViewHolder(binding)
            view.tag = menuViewHolder
        } else {
            view = convertView
            menuViewHolder = convertView.tag as DrawerMenuViewHolder
        }

        val item = getItem(position)
        menuViewHolder.apply {
            itemTextView.text = item.title
            itemIcon.setImageDrawable(item.icon)
        }

        return view
    }


    override fun getItem(position: Int) = item[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = item.size


}