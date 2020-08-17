package com.olzumzum.booklib.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.*

class ViewModelAdapter: RecyclerView.Adapter<ViewModelAdapter.ViewHolder>() {

    data class CellInfo(val layoutId: Int, val bindingId: Int)

    protected val items = LinkedList<Any>()

    //хранит пары разметка - данные для биндинга
    private val cellMap = Hashtable<Class<out Any>, CellInfo>()

    /**
     * layoutid - соответствующая разметка
     * bindingid - идентификатор, для получения данных в holder для биндинга
     */
    protected fun cell(clazz: Class<out Any>, @LayoutRes layoutId: Int, bindingId: Int){
        cellMap[clazz] = CellInfo(layoutId, bindingId)
    }

    /**
     * возвращает layout и binding для соответсвующего viewmodel
     */
    protected fun getCellInfo(viewModel: Any): CellInfo{
        cellMap.entries
            .filter { it.key == viewModel.javaClass }
            .first{return it.value}

        throw Exception("Cell info for class ${viewModel.javaClass.name} not found")
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = DataBindingUtil.bind<ViewDataBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder != null){
            val cellInfo= getCellInfo(items[position])
            if(cellInfo.bindingId != 0)
                holder.binding?.setVariable(cellInfo.bindingId, items[position])
        }
    }

    /**
     * layoutId уникален для разных ячеек
     */
    override fun getItemViewType(position: Int): Int {
        return getCellInfo(items[position]).layoutId
    }
}