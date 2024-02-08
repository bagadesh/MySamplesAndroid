package com.bagadesh.myallsampleapps.mainScreen.paging3.lazyLoad

import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bagadesh on 01/12/22.
 */
class LazyLoadAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        val DIFF_UTIL = object: DiffUtil.ItemCallback<LazyLoadData>() {
            override fun areItemsTheSame(oldItem: LazyLoadData, newItem: LazyLoadData): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: LazyLoadData, newItem: LazyLoadData): Boolean {
                TODO("Not yet implemented")
            }
        }

    }

    private val mDiffer = AsyncListDiffer(this, DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomLazyViewHolder(context = parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomLazyViewHolder).bind(lazyLoadData = mDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }
}