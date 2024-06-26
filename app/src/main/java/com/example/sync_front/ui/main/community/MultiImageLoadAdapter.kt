package com.example.sync_front.ui.main.community

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sync_front.R
import com.example.sync_front.ui.main.community.image.ImageDetailActivity

class MultiImageLoadAdapter(private val mData: List<String>, private val mContext: Context) :
    RecyclerView.Adapter<MultiImageLoadAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiImageLoadAdapter.ViewHolder {
        val context: Context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_multi_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MultiImageLoadAdapter.ViewHolder, position: Int) {
        val imageUri: String = mData[position]
        Log.d("my log", ""+imageUri)

        Glide.with(mContext)
            .load(imageUri)
            .skipMemoryCache(true)  // 예외처리(필수x)
            .diskCacheStrategy(DiskCacheStrategy.NONE)  // 예외처리(필수x)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, ImageDetailActivity::class.java)
            intent.putExtra("imageUri", imageUri.toString())

            // Context가 Activity가 아닌 경우 FLAG_ACTIVITY_NEW_TASK 추가
            if (mContext !is android.app.Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}