package com.syukron.submission1_bajp_dicoding.adapter

import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.model.EntityData
import kotlinx.android.synthetic.main.item_row_movies.view.*

class AdapterMovie : RecyclerView.Adapter<AdapterMovie.MovieViewHolder>() {

    private val dataEntity = ArrayList<EntityData>()
    private lateinit var viewClickListener: ViewClickListener

    fun setData(dataEntities: List<EntityData>){
        this.dataEntity.clear()
        this.dataEntity.addAll(dataEntities)
    }

    fun setOnViewClickListener(viewClickListener: ViewClickListener){
        this.viewClickListener = viewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataEntity[position])
    }

    override fun getItemCount(): Int = dataEntity.size

    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(dataEntity: EntityData){
            with(itemView){
                Glide.with(this).load(dataEntity.imgPoster).into(img_item_photo)
                tv_title.text = dataEntity.title
                tv_genre.text = dataEntity.genre
            }
            itemView.setOnClickListener{
                viewClickListener.onClick(dataEntity)
            }
        }
    }

    interface ViewClickListener{
        fun onClick(dataEntity: EntityData)
    }

}