package com.cristian.appgym.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cristian.appgym.R

class AvatarAdapter(
    private val onAvatarSelected: (String) -> Unit
) : RecyclerView.Adapter<AvatarAdapter.AvatarViewHolder>() {
    
    private val avatars = listOf(
        "gym1", "gym2", "gym3", "gym4", "gym5", "gym6",
        "gym7", "gym8", "gym9", "gym10", "gym11", "gym12"
    )
    
    class AvatarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_avatar, parent, false)
        return AvatarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val seed = avatars[position]
        val url = "https://api.dicebear.com/7.x/adventurer/png?seed=$seed&size=128"
        
        Log.d("AvatarAdapter", "Cargando avatar: $url")
        
        Glide.with(holder.itemView.context)
            .load(url)
            .into(holder.ivAvatar)
            
        holder.itemView.setOnClickListener { onAvatarSelected(seed) }
    }

    override fun getItemCount() = avatars.size
} 