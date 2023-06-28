package com.dicoding.picodiploma.aplikasigithubuser
import android.content.Intent
import android.provider.Telephony.Mms.Intents
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class UserAdapter(private val listReview: List<ItemsItem>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_row, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tvItem.text = listReview[position]?.login
        Glide.with(viewHolder.itemView.context).load(listReview[position]?.avatarUrl)
            .transition(DrawableTransitionOptions.withCrossFade()).centerCrop()
            .into(viewHolder.ivPhoto)

        viewHolder.itemView.setOnClickListener{
            val users = Intent(viewHolder.itemView.context, DetailUser::class.java)
            val url = Intent(viewHolder.itemView.context, DetailUser::class.java)
            users.putExtra(DetailUser.EXTRA_USERNAME,listReview[position]?.login)
            url.putExtra(DetailUser.EXTRA_URL,listReview[position]?.url)
            viewHolder.itemView.context.startActivity(users)
        }
    }

    override fun getItemCount() = listReview.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto: ImageView = view.findViewById(R.id.ivPicture)
        val tvItem: TextView = view.findViewById(R.id.tvName)
    }

}

